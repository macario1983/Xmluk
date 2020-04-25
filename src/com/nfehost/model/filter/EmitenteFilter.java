package com.nfehost.model.filter;

import com.nfehost.util.StringUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class EmitenteFilter extends FilterNfeHost {

	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = StringUtil.aplicarTrimNull(razaoSocial);
	}

	public void setCnpj(String cnpj) {
		this.cnpj = StringUtil.aplicarTrimNull(cnpj);
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = StringUtil.aplicarTrimNull(inscricaoEstadual);
	}

}
