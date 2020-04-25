package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.issqn;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ISSQN")
public class Issqn {

	@XmlElement(name = "vBC")
	private BigDecimal valorBaseCalculo;

	@XmlElement(name = "vAliq")
	private Double valorAliquota;

	@XmlElement(name = "vISSQN")
	private BigDecimal valorIssqn;

}
