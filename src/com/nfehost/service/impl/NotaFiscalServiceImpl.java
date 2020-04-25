package com.nfehost.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.context.FacesContext;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.dao.NotaFiscalDAO;
import com.nfehost.exception.NfeHostException;
import com.nfehost.model.Destinatario;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;
import com.nfehost.model.filter.NotaFiscalFilter;
import com.nfehost.service.NotaFiscalService;
import com.nfehost.service.processor.extraction.NFeProc;
import com.nfehost.service.report.Report;
import com.nfehost.util.FileUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.util.ReportUtil;
import com.nfehost.util.StringUtil;
import com.nfehost.util.XmlUtil;

@EqualsAndHashCode(callSuper = false)
@Data
public class NotaFiscalServiceImpl<Pojo extends NotaFiscal, Dao extends NotaFiscalDAO<Pojo>> extends ServiceImpl<Pojo, Dao> implements NotaFiscalService<Pojo> {
	
	@Override
	protected void validateSave(Pojo pojo, Class<?>... groups) throws NfeHostException {

		NfeHostException exception = new NfeHostException();	
		
		Emitente emitente = this.getDao().hasEmitente(pojo.getEmitente());
		
		if (!NullUtil.isNull(emitente)) {
			pojo.setEmitente(emitente);;
		}
		
		Destinatario destinatario = this.getDao().hasDestinatario(pojo.getDestinatario());
		
		if (!NullUtil.isNull(destinatario)) {
			pojo.setDestinatario(destinatario);
		}
		
		if (this.getDao().hasNotaFiscal(pojo)) {
			exception.addMessageError("Nota Fiscal sendo cadastrada novamente, esta ação não sera realizada");
		}
		
		if (!NullUtil.isNull(exception.getMessages()) && !exception.getMessages().isEmpty()) {
			throw exception;
		}

		super.validateSave(pojo, groups);
	}

	@SuppressWarnings("unchecked")
	public List<NotaFiscal> getListNotasFiscaisByEmitente(NotaFiscalFilter filter) {

		if (!NullUtil.isNull(filter)) {
			return (List<NotaFiscal>) this.getDao().findByFilter(filter);
		}
		return null;
	}

	public List<NotaFiscal> getListNotasFiscaisByEmitente(Emitente emitente) {
		return (List<NotaFiscal>) this.getDao().getNotaFiscalByEmitente(emitente);
	}

	public NotaFiscal getNotaFiscal(Long id) {
		return this.getDao().findById(id);
	}

	@Override
	public void openJasperReportPdf(NotaFiscal notaFiscal) {
		NFeProc nFeProc = XmlUtil.parseToString(notaFiscal.getArquivoNotaFiscal().getConteudo());
		byte[] byteArray = new Report().pdfBytes(nFeProc);
		ReportUtil.downloadJasperReportPdf(byteArray, notaFiscal.getArquivoNotaFiscal().getNome(), FacesContext.getCurrentInstance());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validateXmlFile(Entry<String, String> attachment) {
		
		Pojo pojo = null;
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
				
				pojo = (Pojo) XmlUtil.createNotaFiscal(attachment);
				
				try {
					this.save(pojo);
					cnpj = pojo.getEmitente().getDadosEmpresa().getCnpj();
				} catch (NfeHostException e) {
					e.printStackTrace();
				}
				
				cnpj = pojo.getEmitente().getDadosEmpresa().getCnpj();					
			}
			
		}		

		Path path = FileUtil.getXmlDirectory(isValidate ? "Valida" : "Invalida", StringUtil.stringNotNull(versionElement), StringUtil.stringNotNull(rootElement), cnpj);
		
		if (FileUtil.directoryNotExist(path.toAbsolutePath())) {
			FileUtil.createDirectory(path);
		}
		
		FileUtil.createFile(attachment.getValue(), filename, path);			
	}

	@Override
	public List<Path> getSourceNotasFiscais(Set<Long> setNotasFiscais) {
		
		List<NotaFiscal> listNotaFiscais = this.getDao().getSourceNotasFiscais(setNotasFiscais);
		List<Path> listPaths = new ArrayList<>(listNotaFiscais.size());
		String fileName;
		String source;
		Path pathFile;
		
		for (NotaFiscal notaFiscal : listNotaFiscais) {
			
			fileName = notaFiscal.getArquivoNotaFiscal().getNome();
			source = notaFiscal.getArquivoNotaFiscal().getConteudo();
			pathFile = FileUtil.createFile(source, fileName, Paths.get(FileUtil.getTempFolder()));
			listPaths.add(pathFile);

		}
		
		return listPaths;
	}
}
