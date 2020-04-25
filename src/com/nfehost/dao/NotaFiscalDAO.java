package com.nfehost.dao;

import java.util.List;
import java.util.Set;

import com.nfehost.model.Destinatario;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;

public interface NotaFiscalDAO<Pojo extends NotaFiscal> extends DAO<Pojo> {
	
	boolean hasNotaFiscal(Pojo pojo);
	Destinatario hasDestinatario(Destinatario destinatario);
	Emitente hasEmitente(Emitente emitente);
	List<NotaFiscal> getNotaFiscalByEmitente(Emitente emitenteNota);
	List<NotaFiscal> getSourceNotasFiscais(Set<Long> setNotasFiscais);
}
