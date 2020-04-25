package com.nfehost.service.impl;

import java.util.List;

import com.nfehost.dao.ContatoDAO;
import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;
import com.nfehost.service.ContatoService;

public class ContatoServiceImpl<Pojo extends Contato, Dao extends ContatoDAO<Pojo>> extends ServiceImpl<Pojo, Dao> implements ContatoService<Pojo> {
	
	public List<Long> getListEmitenteWithContato() {
		return this.getDao().getListEmitenteWithContato();
	}

	@Override
	public List<Contato> getListContatoByEmitente(Emitente emitenteContato) {
		return this.getDao().getListContatoByEmitente(emitenteContato);
	}

}
