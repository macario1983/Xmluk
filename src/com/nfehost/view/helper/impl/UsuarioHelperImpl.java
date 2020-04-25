package com.nfehost.view.helper.impl;

import com.nfehost.model.Usuario;
import com.nfehost.model.Usuario.Nivel;
import com.nfehost.view.helper.UsuarioHelper;

public class UsuarioHelperImpl<Pojo extends Usuario> implements UsuarioHelper<Pojo> {

	@Override
	public void fillDefaults() {
		
	}

	@Override
	public Nivel[] getNiveisUsuario() {
		return Nivel.values();
	}

}
