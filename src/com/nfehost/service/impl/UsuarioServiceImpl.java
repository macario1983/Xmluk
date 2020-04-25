package com.nfehost.service.impl;

import com.nfehost.dao.UsuarioDAO;
import com.nfehost.exception.NfeHostException;
import com.nfehost.model.Usuario;
import com.nfehost.service.UsuarioService;
import com.nfehost.util.NullUtil;

public class UsuarioServiceImpl<Pojo extends Usuario, Dao extends UsuarioDAO<Pojo>> extends ServiceImpl<Pojo, Dao> implements UsuarioService<Pojo> {

	@Override
	protected void validateSave(Pojo pojo, Class<?>... groups) throws NfeHostException {
	
		NfeHostException exception = new NfeHostException();
				
		if (!NullUtil.isNull(userExists(pojo.getLogin(), null))) {
			exception.addMessageError("ERRO.USUARIO.EXISTE");
		}

		if (!NullUtil.isNull(exception.getMessages()) && !exception.getMessages().isEmpty()) {
			throw exception;
		}
		
		super.validateSave(pojo, groups);
	}
	
	@Override
	public Usuario userExists(String username, String password) {
		return this.getDao().userExists(username, password);
	}

}
