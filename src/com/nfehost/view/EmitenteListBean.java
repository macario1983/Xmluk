package com.nfehost.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Emitente;
import com.nfehost.model.filter.EmitenteFilter;
import com.nfehost.service.EmitenteService;
import com.nfehost.view.helper.EmitenteHelper;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmitenteListBean<Pojo extends Emitente, Services extends EmitenteService<Pojo>, Helper extends EmitenteHelper<Pojo>, Filter extends EmitenteFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {

	private Emitente emitente;
	
	public void findDadosEmitente(Long id) {
		this.setEmitente(this.getService().getEmitente(id));
	}

}
