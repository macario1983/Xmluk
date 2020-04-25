package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class IcmsInteface {

	@XmlElement(name = "orig")
	private Integer orig;
	
	@XmlElement(name = "CST")
	private Integer cst;

}
