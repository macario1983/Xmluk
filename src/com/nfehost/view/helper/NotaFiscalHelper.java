package com.nfehost.view.helper;

import java.util.List;

import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;

public interface NotaFiscalHelper<Pojo extends NotaFiscal> extends FormHelper<Pojo> {

	List<Emitente> getListEmitentes();

}
