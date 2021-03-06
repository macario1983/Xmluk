package com.nfehost.view;

import com.nfehost.model.Contato;
import com.nfehost.model.filter.ContatoFilter;
import com.nfehost.service.ContatoService;
import com.nfehost.view.helper.ContatoHelper;

public class ContatoListBean<Pojo extends Contato, Services extends ContatoService<Pojo>, Helper extends ContatoHelper<Pojo>, Filter extends ContatoFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {

	public void setPojoId(Long pojoId) {
		this.getPojo().setId(pojoId);
	}
}
