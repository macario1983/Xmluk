package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins.cofinsoutr;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins.CofinsInterface;

@EqualsAndHashCode(callSuper = false)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COFINSOutr")
public class CofinsOutr extends CofinsInterface {

	@XmlElement(name = "vBC")
	private BigDecimal valorBaseCalculo;

	@XmlElement(name = "pCOFINS")
	private Double percentual;

	@XmlElement(name = "qBCProd")
	private Double quantidadeVendida;

	@XmlElement(name = "vAliqProd")
	private BigDecimal valorAliquotaProduto;

	@XmlElement(name = "vCOFINS")
	private BigDecimal valor;

}
