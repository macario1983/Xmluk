package com.nfehost.view.helper;

import com.nfehost.model.Usuario;
import com.nfehost.model.Usuario.Nivel;

public interface UsuarioHelper<Pojo extends Usuario> extends FormHelper<Pojo> {

	Nivel[] getNiveisUsuario();

}
