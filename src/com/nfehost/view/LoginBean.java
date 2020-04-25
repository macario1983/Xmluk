package com.nfehost.view;

import javax.faces.application.FacesMessage;

import lombok.Data;

import com.nfehost.exception.NfeHostException;
import com.nfehost.model.Usuario;
import com.nfehost.model.Usuario.Nivel;
import com.nfehost.service.UsuarioService;
import com.nfehost.util.FaceHttpParameterUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.view.helper.UsuarioHelper;

@Data
public class LoginBean {

	private String username;
	private String password;
	private UsuarioService<Usuario> service;
	private UsuarioHelper<Usuario> helper;
	private Usuario usuario;
	private boolean logged;

	public String login() throws NfeHostException {

		usuario = this.getService().userExists(this.getUsername(), this.getPassword());

		if (NullUtil.isNull(usuario)) {
			FaceHttpParameterUtil.addMessageFromBundleInFlash(FacesMessage.SEVERITY_ERROR, "ERRO.LOGIN.INVALIDO");
		}

		return this.home();
	}

	public String home() {

		if (NullUtil.isNull(this.getUsuario())) {
			this.setLogged(false);
			return "loginInvalido";
		}
		
		this.setLogged(true);
		return "loginValido";
	}
	
	
	public boolean isAdministrador() {
		
		if (!this.isLogged()) {
			return true;
		}
		
		return this.usuario.getNivel().equals(Nivel.ADMINISTRADOR);
	}

}
