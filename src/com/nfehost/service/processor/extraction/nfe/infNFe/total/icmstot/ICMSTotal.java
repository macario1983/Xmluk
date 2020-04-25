package com.nfehost.service.processor.extraction.nfe.infNFe.total.icmstot;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ICMSTot")
public class ICMSTotal {

	@XmlElement(name = "vBC")
	private BigDecimal valorBaseCalculo;
	
	@XmlElement(name = "vICMS")
	private BigDecimal valorICMS;
	
	@XmlElement(name = "vBCST")
	private BigDecimal valorBaseCalculoST;
	
	@XmlElement(name = "vProd")
	private BigDecimal valorProduto;
	
	@XmlElement(name = "vFrete")
	private BigDecimal valorFrete;
	
	@XmlElement(name = "vSeg")
	private BigDecimal valorSeguro;
	
	@XmlElement(name = "vDesc")
	private BigDecimal valorDesconto;
	
	@XmlElement(name = "vIPI")
	private BigDecimal valorIPI;
	
	@XmlElement(name = "vOutro")
	private BigDecimal valorOutro;

	@XmlElement(name = "vNF")
	private BigDecimal valorNotaFiscal;
	
	@XmlElement(name = "vICMSST")
	private BigDecimal valorICMSST;

}
