package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms20;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.IcmsInteface;

@EqualsAndHashCode(callSuper = false)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "icms20")
public class ICMS20 extends IcmsInteface {

	@XmlElement(name = "modBC")
	private Integer modBC;
	
	@XmlElement(name = "pRedBC")
	private double percentualReducao;
	
	@XmlElement(name = "vBC")
	private BigDecimal valorBaseCalculo;
	
	@XmlElement(name = "pICMS")
	private Double percentualIcms;
	
	@XmlElement(name = "vICMS")
	private BigDecimal valorIcms;

}
