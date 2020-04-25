package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class PisInterface {
	
	@XmlElement(name = "CST")
	private Integer cst;

}
