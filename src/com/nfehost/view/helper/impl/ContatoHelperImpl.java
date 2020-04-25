package com.nfehost.view.helper.impl;

import java.util.List;

import lombok.Data;

import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;
import com.nfehost.service.EmitenteService;
import com.nfehost.view.helper.ContatoHelper;

@Data
public class ContatoHelperImpl<Pojo extends Contato> implements ContatoHelper<Pojo> {

	private EmitenteService<Emitente> emitenteService;

	@Override
	public void fillDefaults() {

	}

	public List<Emitente> getListEmitentes() {
		return this.getEmitenteService().findListEmitentes();
	}

}
