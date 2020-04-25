package com.nfehost.model.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Usuario.Nivel;
import com.nfehost.util.StringUtil;

@EqualsAndHashCode(callSuper = false)
@Data
public class UsuarioFilter extends FilterNfeHost {

	private String username;
	private Nivel nivel;

	public String getUsername() {
		return StringUtil.aplicarTrimNull(username);
	}

}
