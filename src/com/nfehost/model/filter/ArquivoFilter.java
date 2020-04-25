package com.nfehost.model.filter;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ArquivoFilter extends FilterNfeHost {
	
	Date dataInicial;
	Date dataFinal;

}
