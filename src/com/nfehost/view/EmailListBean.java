package com.nfehost.view;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import lombok.Data;

import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;
import com.nfehost.model.filter.EmailFilter;
import com.nfehost.util.EmailUtil;
import com.nfehost.util.FaceHttpParameterUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;
import com.nfehost.view.helper.EmailHelper;

@Data
public class EmailListBean {

	private String contato = "";
	private String contatoCopia;

	private String copiaOculta;
	private String titulo;
	private String mensagem;
	
	private Set<String> setEmails;
	private Set<Long> setNotaFiscal;
	
	private boolean show;

	private EmailHelper helper;
	private EmailFilter filter;
	
	private Emitente emitenteNota;
	private Emitente emitenteContato;

	
	public void onRowSelectContato () {
		
		if (NullUtil.isNull(this.setEmails)) {
			this.setEmails = new HashSet<>();
		}
		
		for (Contato singleContato : this.getFilter().getListContatos()) {
			this.setEmails.add(singleContato.getEmail());
		}
		
		this.setContato("");
		
		for (String email : this.getSetEmails()) {
			this.contato = StringUtil.isStringNullOrEmpty(this.contato) ? this.contato.concat(email) : this.contato.concat(",").concat(email);					
		}
	}

	public void onRowSelectNotaFiscal () {
		
		if (NullUtil.isNull(this.setNotaFiscal)) {
			this.setNotaFiscal = new HashSet<>();
		}
		
		for(NotaFiscal notaFiscal: this.getFilter().getListNotasFiscais()) {
			this.getSetNotaFiscal().add(notaFiscal.getId());
		}
				
	}
	
	public void enviar() {
		
		try {

			Message message = new MimeMessage(EmailUtil.getEmailSession());
			message.setFrom(new InternetAddress("xmluka@gmail.com"));
			InternetAddress[] adress = this.processEmail();
			
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setText(this.getMensagem());
			MimeBodyPart mimeBodyPartFile = new MimeBodyPart();
			
			List<Path> listPaths = this.processNotaFiscal();
			Multipart multipart = new MimeMultipart();
			
			if (!NullUtil.isEmpty(listPaths)) {
				
				for (Path path : listPaths) {

					FileDataSource fileDataSource = new FileDataSource(new File(path.toString()));
					mimeBodyPartFile.setDataHandler(new DataHandler(fileDataSource));
					mimeBodyPartFile.setFileName(fileDataSource.getName());							
					multipart.addBodyPart(mimeBodyPart);
					multipart.addBodyPart(mimeBodyPartFile);		
					message.setSubject(fileDataSource.getName());
				}
			}
						
			message.setContent(multipart);
			message.setRecipients(Message.RecipientType.TO, adress);
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	private InternetAddress[] processEmail () {
		
		String []contatos = null;
		
		if (!NullUtil.isEmpty(this.getSetEmails())) {
			contatos = this.contato.split(",");
			
			InternetAddress[] adress = new InternetAddress[contatos.length]; 
			
			for (int i = 0; i < adress.length; i++) {
	
				try {
					adress[i] = new InternetAddress(contatos[i]);
				} catch (AddressException e) {
					e.printStackTrace();
				}
			}
			
			return adress;
		}
		return null;
	}

	private List<Path> processNotaFiscal () {
		
		if (!NullUtil.isEmpty(this.getSetNotaFiscal())) {
			return this.getHelper().getSourceNotasFiscais(this.getSetNotaFiscal());
		}
			
		return null;
	}

	public boolean showListener() {
		this.setShow(true);
		return this.isShow();
	}

	public void sendEmail() {

		if (StringUtil.isStringNullOrEmpty(this.getContato())) {
			FaceHttpParameterUtil.addMessageFromBundleInFlash(FacesMessage.SEVERITY_ERROR, "ERRO.CONTATO.VAZIO");
		}

		if (StringUtil.isStringNullOrEmpty(this.getTitulo())) {
			FaceHttpParameterUtil.addMessageFromBundleInFlash(FacesMessage.SEVERITY_ERROR, "ERRO.TITULO.VAZIO");
		}

		if (StringUtil.isStringNullOrEmpty(this.getMensagem())) {
			FaceHttpParameterUtil.addMessageFromBundleInFlash(FacesMessage.SEVERITY_ERROR, "ERRO.MENSAGEM.VAZIO");
		}

	}
			
	public List<Contato> getListContatos() {
		return this.getHelper().getListContatosFiltered(this.getFilter().getEmitenteContato());
	}
	
	public List<NotaFiscal> getListNotasFiscais() {
		return this.getHelper().getListNotasFiscaisFiltered(this.getFilter().getEmitenteNota());
	}
}
