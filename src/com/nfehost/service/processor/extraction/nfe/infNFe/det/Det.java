package com.nfehost.service.processor.extraction.nfe.infNFe.det;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.Imposto;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.prod.Prod;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Det")
public class Det {
	
	@XmlElement(name = "prod")
	private Prod prod;
	
	@XmlElement(name = "imposto")
	private Imposto imposto;

}
