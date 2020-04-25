package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms90;

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
@XmlType(name = "icms90")
public class ICMS90 extends IcmsInteface {
	
	@XmlElement(name = "modBC")
	private Integer modBC;
	
	@XmlElement(name = "vBC")
	private BigDecimal valorBaseCalculo;
	
	@XmlElement(name = "pICMS")
	private Double percentualIcms;
	
	@XmlElement(name = "vICMS")
	private BigDecimal valorIcms;
	
	@XmlElement(name = "modBCST")
	private Integer modalidade;

	@XmlElement(name = "pMVAST")
	private Double percentualMargemValorAdicionadoIcmsSt;

	@XmlElement(name = "pRedBCST")
	private Double percentualReducaoValorAdicionadoIcmsSt;

	@XmlElement(name = "vBCST")
	private BigDecimal valorBaseCalculoST;

	@XmlElement(name = "pICMSST")
	private Double percentualIcmsSt;

	@XmlElement(name = "vICMSST")
	private BigDecimal valorIcmsSt;

}
