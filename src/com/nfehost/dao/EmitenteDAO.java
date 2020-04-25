package com.nfehost.dao;

import java.util.List;

import com.nfehost.model.Emitente;

public interface EmitenteDAO<Pojo extends Emitente> extends DAO<Pojo> {
	
	List<Emitente> getListEmitenteWithContato(List<Long> listIds);
	
}
