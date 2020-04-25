package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms40;

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
@XmlType(name = "icms40")
public class ICMS40 extends IcmsInteface {
	
	@XmlElement(name = "vICMS")
	private BigDecimal valorIcms;
	
	@XmlElement(name = "motDesICMS")
	private Integer motivoDesoneracaoIcms;

}
