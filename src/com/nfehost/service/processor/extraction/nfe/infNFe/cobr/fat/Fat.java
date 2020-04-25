package com.nfehost.service.processor.extraction.nfe.infNFe.cobr.fat;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fat")
public class Fat {

	@XmlElement(name = "nFat")
	private String numeroFatura;

	@XmlElement(name = "vOrig")
	private BigDecimal valorOriginal;

	@XmlElement(name = "vLiq")
	private BigDecimal valorLiquido;

}
