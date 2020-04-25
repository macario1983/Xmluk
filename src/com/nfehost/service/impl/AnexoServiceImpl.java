package com.nfehost.service.impl;

import java.nio.file.Path;
import java.util.Map.Entry;

import lombok.Data;

import com.nfehost.dao.AnexoDAO;
import com.nfehost.exception.NfeHostException;
import com.nfehost.model.Destinatario;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;
import com.nfehost.service.AnexoService;
import com.nfehost.util.FileUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;
import com.nfehost.util.XmlUtil;

@Data
public class AnexoServiceImpl implements AnexoService {

	private AnexoDAO dao;

	public void validateSave(NotaFiscal notaFiscal) throws NfeHostException {
		
		NfeHostException exception = new NfeHostException();
				
		Emitente emitente = this.getDao().hasEmitente(notaFiscal.getEmitente());
		
		if (!NullUtil.isNull(emitente)) {

			notaFiscal.setEmitente(emitente);

		}
		
		Destinatario destinario = this.getDao().hasDestinatario(notaFiscal.getDestinatario());
		
		if (!NullUtil.isNull(destinario)) {
			
			notaFiscal.setDestinatario(destinario);

		}
		
		if (this.getDao().hasNotaFiscal(notaFiscal)) {
			exception.addMessageError("Nota Fiscal sendo cadastrada novamente, esta ação não sera realizada");
		}
		
		if (!NullUtil.isNull(exception.getMessages()) && !exception.getMessages().isEmpty()) {
			throw exception;
		}

	}
	
	@Override
	public void validateXmlFile(Entry<String, String> attachment) {
		
		NotaFiscal notaFiscal = null;
		String cnpj = "";
		String filename = attachment.getKey();
		String source = attachment.getValue();
		String rootElement = XmlUtil.getRootElement(source);
		String versionElement = XmlUtil.getVersionElement(source);
		boolean isValidate;
		
		String xsdFilename = XmlUtil.getXsdFilename(rootElement, versionElement);

		if (isValidate = (!StringUtil.isStringNullOrEmpty(xsdFilename)) && 
						 (!StringUtil.isStringNullOrEmpty(versionElement)) &&
						 (XmlUtil.xmlIsValidates(source, filename, versionElement))) { 
			
			if (XmlUtil.isRootElementNfeProc(source)) {
				
				notaFiscal = XmlUtil.createNotaFiscal(attachment);
				
				try {
					this.save(notaFiscal);
					cnpj = notaFiscal.getEmitente().getDadosEmpresa().getCnpj();
				} catch (NfeHostException e) {
					e.printStackTrace();
				}
				
				cnpj = notaFiscal.getEmitente().getDadosEmpresa().getCnpj();					
			}
			
		}		

		Path path = FileUtil.getXmlDirectory(isValidate ? "Valida" : "Invalida", StringUtil.stringNotNull(versionElement), StringUtil.stringNotNull(rootElement), cnpj);
		
		if (FileUtil.directoryNotExist(path.toAbsolutePath())) {
			FileUtil.createDirectory(path);
		}
		
		FileUtil.createFile(attachment.getValue(), filename, path);			

	}
	
	private void save(NotaFiscal notaFiscal) throws NfeHostException {
		this.validateSave(notaFiscal);
		this.getDao().save(notaFiscal);
	}
	
}
