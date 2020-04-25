package com.nfehost.service.processor.extraction.nfe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.InformacoesNFe;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NFe")
public class NFe {

	@XmlElement(name = "infNFe")
	private InformacoesNFe infNFe;

}
