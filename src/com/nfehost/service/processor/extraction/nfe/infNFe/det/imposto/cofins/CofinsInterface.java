package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CofinsInterface {

	@XmlElement(name = "CST")
	private Integer cst;

}
