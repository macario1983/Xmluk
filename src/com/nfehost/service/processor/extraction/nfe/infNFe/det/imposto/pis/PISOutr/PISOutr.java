package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.PISOutr;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.PisInterface;

@EqualsAndHashCode(callSuper = false)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PISOutr")
public class PISOutr extends PisInterface {
	
	@XmlElement(name = "vBC")
	private BigDecimal valorBaseCalculo;

	@XmlElement(name = "pPIS")
	private Double percentual;

	@XmlElement(name = "qBCProd")
	private Double quantidadeVendida;

	@XmlElement(name = "vPIS")
	private BigDecimal valorAliquotaProduto;

}
