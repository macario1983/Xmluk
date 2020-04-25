package com.nfehost.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.dao.DestinatarioDAO;
import com.nfehost.model.Destinatario;
import com.nfehost.service.DestinatarioService;

@EqualsAndHashCode(callSuper = false)
@Data
public class DestinatarioServiceImpl<Pojo extends Destinatario, Dao extends DestinatarioDAO<Pojo>> extends ServiceImpl<Pojo, Dao> implements DestinatarioService<Pojo> {@Override

	public Destinatario getDestinatario(Long id) {
		return this.getDao().findById(id);
	}

}
