package com.nfehost.service.processor.extraction.protnfe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.protnfe.infprot.InfProt;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "protNFe")
public class ProtNFe {

	@XmlElement(name = "infProt")
	private InfProt infProt;

}
