package com.nfehost.view;

import javax.faces.application.FacesMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Usuario;
import com.nfehost.model.filter.UsuarioFilter;
import com.nfehost.service.UsuarioService;
import com.nfehost.util.FaceHttpParameterUtil;
import com.nfehost.view.helper.UsuarioHelper;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioBean<Pojo extends Usuario, Services extends UsuarioService<Pojo>, Helper extends UsuarioHelper<Pojo>, Filter extends UsuarioFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {
	
	private String confirmPassword;
	
	@Override
	public void save() {

		if (!this.getPojo().getSenha().equals(this.getConfirmPassword())) {
			FaceHttpParameterUtil.addMessageFromBundleInFlash(FacesMessage.SEVERITY_ERROR, "ERRO.USUARIO.COINCIDEM");
		} else {
			super.save();
		}
					
	}
	
}
