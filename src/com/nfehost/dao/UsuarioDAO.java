package com.nfehost.dao;

import com.nfehost.model.Usuario;

public interface UsuarioDAO<Pojo extends Usuario> extends DAO<Pojo> {

	Usuario userExists(String username, String password);

}
