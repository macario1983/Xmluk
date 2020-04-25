package com.nfehost.service;

import java.util.List;

import com.nfehost.model.Emitente;

public interface EmitenteService<Pojo extends Emitente> extends Service<Pojo> {
	
	List<Emitente> findListEmitentes();
	Emitente getEmitente(Long id);
	List<Emitente> getListEmitenteWithContato(List<Long> listIds);
}
