package com.nfehost.service;

import com.nfehost.model.Destinatario;

public interface DestinatarioService<Pojo extends Destinatario> extends Service<Pojo> {
	
	Destinatario getDestinatario(Long id);
	
}
