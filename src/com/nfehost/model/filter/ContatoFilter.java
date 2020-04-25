package com.nfehost.model.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Emitente;
import com.nfehost.util.StringUtil;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContatoFilter extends FilterNfeHost {

	String nome;
	String email;
	String telefone;
	Emitente emitente;

	public void setNome(String nome) {
		this.nome = StringUtil.aplicarTrimNull(nome);
	}

	public void setEmail(String email) {
		this.email = StringUtil.aplicarTrimNull(email);
	}

	public void setTelefone(String telefone) {
		this.telefone = StringUtil.aplicarTrimNull(telefone);
	}

}
