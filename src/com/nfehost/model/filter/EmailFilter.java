package com.nfehost.model.filter;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;

@EqualsAndHashCode(callSuper = false)
@Data
public class EmailFilter extends FilterNfeHost {

	private List<Contato> listContatos;
	private List<NotaFiscal> listNotasFiscais;
	
	private Emitente emitenteNota;
	private Emitente emitenteContato;
	private Integer numeroNota;
	private Date dataEmissaoNotaInicial;
	private Date dataEmissaoNotaFinal;
	

}
