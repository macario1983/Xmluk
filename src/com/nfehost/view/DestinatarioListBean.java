package com.nfehost.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Destinatario;
import com.nfehost.model.filter.DestinatarioFilter;
import com.nfehost.service.DestinatarioService;
import com.nfehost.view.helper.DestinatarioHelper;

@Data
@EqualsAndHashCode(callSuper = false)
public class DestinatarioListBean<Pojo extends Destinatario, Services extends DestinatarioService<Pojo>, Helper extends DestinatarioHelper<Pojo>, Filter extends DestinatarioFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {

	Destinatario destinatario;
	
	public void findDadosDestinatario(Long id) {
		this.setDestinatario(this.getService().getDestinatario(id));
	}
	
}
