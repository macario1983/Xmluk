package com.nfehost.view.helper;

import java.util.List;

import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;

public interface ContatoHelper<Pojo extends Contato> extends FormHelper<Pojo> {
	
	List<Emitente> getListEmitentes();

}
