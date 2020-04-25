package com.nfehost.service.processor.extraction.nfe.infNFe.det.prod;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prod")
public class Prod {

	@XmlElement(name = "cProd")
	private String codigo;
	
	@XmlElement(name = "cEAN")
	private Long cean;
	
	@XmlElement(name = "xProd")
	private String nome;
	
	@XmlElement(name = "NCM")
	private Integer ncm;
	
	@XmlElement(name = "CFOP")
	private Integer codigoDeOperacaoPrestacao;
	
	@XmlElement(name = "uCom")
	private String unidadeComercial;
	
	@XmlElement(name = "qCom")
	private Double quantidadeComercial;
	
	@XmlElement(name = "vUnCom")
	private BigDecimal valorUnitario;
	
	@XmlElement(name = "vProd")
	private BigDecimal valorTotal;
	
	@XmlElement(name = "uTrib")
	private String unidadeTributavel;
	
	@XmlElement(name = "qTrib")
	private Double quantidadeTributavel;
	
	@XmlElement(name = "vUnTrib")
	private BigDecimal valorUnitarioTributacao;
	
	@XmlElement(name = "Vdesc")
	private BigDecimal valorDesconto;

}
