package com.nfehost.service;

import com.nfehost.model.Usuario;

public interface UsuarioService<Pojo extends Usuario> extends Service<Pojo> {

	Usuario userExists(String username, String password);

}
