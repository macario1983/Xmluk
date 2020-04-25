package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins.Cofins;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.Icms;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.issqn.Issqn;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.Pis;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imposto")
public class Imposto {

	@XmlElement(name = "COFINS")
	private Cofins cofins;

	@XmlElement(name = "ICMS")
	private Icms icms;

	@XmlElement(name = "PIS")
	private Pis pis;
	
	@XmlElement(name = "ISSQN")
	private Issqn issqn;
	
}
