package com.nfehost.service;

import java.util.List;

import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;

public interface ContatoService<Pojo extends Contato> extends Service<Pojo> {

	List<Long> getListEmitenteWithContato();
	List<Contato> getListContatoByEmitente(Emitente emitenteContato);
}
