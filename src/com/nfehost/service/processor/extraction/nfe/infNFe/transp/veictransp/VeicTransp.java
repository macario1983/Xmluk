package com.nfehost.service.processor.extraction.nfe.infNFe.transp.veictransp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "veicTransp")
public class VeicTransp {
	
	@XmlElement(name = "placa")
	private String placa;
	
	@XmlElement(name = "UF")
	private String UF;
	
	@XmlElement(name = "RNTC")
	private String registroANTT;


}
