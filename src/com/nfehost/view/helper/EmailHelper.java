package com.nfehost.view.helper;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;

public interface EmailHelper {
	
	List<NotaFiscal> getListNotasFiscaisFiltered(Emitente emitenteNota);
	List<Emitente> getListEmitentesWithContato();
	List<Contato> getListContatosFiltered(Emitente emitenteContato);
	List<Path> getSourceNotasFiscais(Set<Long> setNotasFiscais); 
}
