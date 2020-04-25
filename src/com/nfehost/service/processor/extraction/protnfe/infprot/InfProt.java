package com.nfehost.service.processor.extraction.protnfe.infprot;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infProt")
public class InfProt {

	@XmlElement(name = "chNFe")
	private String chaveNFe;

	@XmlElement(name = "digVal")
	private String digestValue;
	
	@XmlElement(name = "dhRecbto")
	private Date dataHoraRecebimento;
	
	@XmlElement(name = "nProt")
	private String numeroProtocolo;

}
