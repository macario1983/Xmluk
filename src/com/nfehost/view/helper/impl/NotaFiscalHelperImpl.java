package com.nfehost.view.helper.impl;

import java.util.List;

import lombok.Data;

import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;
import com.nfehost.service.EmitenteService;
import com.nfehost.view.helper.NotaFiscalHelper;

@Data
public class NotaFiscalHelperImpl<Pojo extends NotaFiscal> implements NotaFiscalHelper<Pojo> {

	private EmitenteService<Emitente> emitenteService;

	@Override
	public void fillDefaults() {

	}

	public List<Emitente> getListEmitentes() {
		return this.getEmitenteService().findListEmitentes();
	}

}
