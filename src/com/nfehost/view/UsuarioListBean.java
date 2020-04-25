package com.nfehost.view;

import com.nfehost.model.Usuario;
import com.nfehost.model.filter.UsuarioFilter;
import com.nfehost.service.UsuarioService;
import com.nfehost.view.helper.UsuarioHelper;

public class UsuarioListBean<Pojo extends Usuario, Services extends UsuarioService<Pojo>, Helper extends UsuarioHelper<Pojo>, Filter extends UsuarioFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {

	public void setPojoId(Long pojoId) {
		this.getPojo().setId(pojoId);
	}

}
