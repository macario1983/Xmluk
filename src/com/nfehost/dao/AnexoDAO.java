package com.nfehost.dao;

import com.nfehost.model.Destinatario;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;
//teste svn joao paulo
public interface AnexoDAO {

	Emitente hasEmitente(Emitente emitente);
	Destinatario hasDestinatario(Destinatario destinatario);
	boolean hasNotaFiscal(NotaFiscal notaFiscal);
	void save(NotaFiscal notaFiscal);

}
