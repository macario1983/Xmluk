package com.nfehost.dao;

import java.util.List;

import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;

public interface ContatoDAO<Pojo extends Contato> extends DAO<Pojo> {
	
	List<Long> getListEmitenteWithContato();
	List<Contato> getListContatoByEmitente(Emitente emitenteContato);
	
}
