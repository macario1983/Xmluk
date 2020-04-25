package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.PISAliq.PISAliq;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.PISNT.PISNT;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.PISOutr.PISOutr;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.PISQtde.PISQtde;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PIS")
public class Pis {
	
	@XmlElement(name = "PISAliq")
	private PISAliq pisAliq;
	
	@XmlElement(name = "PISQtde")
	private PISQtde pisQtde;
	
	@XmlElement(name = "PISNT")
	private PISNT pisNt;
	
	@XmlElement(name = "PISOutr")
	private PISOutr pisOutr;

}
