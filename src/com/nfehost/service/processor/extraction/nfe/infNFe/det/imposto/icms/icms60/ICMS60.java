package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms60;

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
@XmlType(name = "icms60")
public class ICMS60 extends IcmsInteface {

	@XmlElement(name = "vBCSTRet")
	private BigDecimal valorBaseCalculoRetida;
	
	@XmlElement(name = "vICMSSTRet")
	private BigDecimal valorIcmsStRetido;
	
}
