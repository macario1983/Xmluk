package com.nfehost.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;
import com.nfehost.model.filter.NotaFiscalFilter;

public interface NotaFiscalService<Pojo extends NotaFiscal> extends Service<Pojo> {

	List<NotaFiscal> getListNotasFiscaisByEmitente(NotaFiscalFilter filter);
	List<NotaFiscal> getListNotasFiscaisByEmitente(Emitente emitente);
	NotaFiscal getNotaFiscal(Long id);
	void openJasperReportPdf(NotaFiscal notaFiscal);
	void validateXmlFile(Entry<String, String> attachment);
	List<Path> getSourceNotasFiscais(Set<Long> setNotasFiscais); 
	
}
