package com.nfehost.service.processor.extraction.nfe.infNFe.cobr.dup;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dup")
public class Dup {
	
	@XmlElement(name = "nDup")
	private String numero;
		
	@XmlElement(name = "dVenc")
	private Date dataVencimento;
	
	@XmlElement(name = "vDup")
	private BigDecimal valor;

}
