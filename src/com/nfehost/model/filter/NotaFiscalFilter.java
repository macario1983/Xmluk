package com.nfehost.model.filter;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Emitente;

@EqualsAndHashCode(callSuper = false)
@Data
public class NotaFiscalFilter extends FilterNfeHost {

	Emitente emitente;
	Integer numeroNota;
	Date dataEmissaoNotaInicial;
	Date dataEmissaoNotaFinal;

}
