package com.nfehost.service.impl;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.dao.EmitenteDAO;
import com.nfehost.model.Emitente;
import com.nfehost.service.EmitenteService;

@EqualsAndHashCode(callSuper = false)
@Data
public class EmitenteServiceImpl<Pojo extends Emitente, Dao extends EmitenteDAO<Pojo>> extends ServiceImpl<Pojo, Dao> implements EmitenteService<Pojo> {

	@SuppressWarnings("unchecked")
	public List<Emitente> findListEmitentes() {
		return (List<Emitente>) super.findAll();
	}

	@Override
	public Emitente getEmitente(Long id) {
		return this.getDao().findById(id);
	}
	
	public List<Emitente> getListEmitenteWithContato(List<Long> listIds) {
		return this.getDao().getListEmitenteWithContato(listIds);
	}

}
