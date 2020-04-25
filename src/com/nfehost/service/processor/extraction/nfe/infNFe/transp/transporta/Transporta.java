package com.nfehost.service.processor.extraction.nfe.infNFe.transp.transporta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transporta")
public class Transporta {

	@XmlElement(name = "CNPJ")
	private String cnpj;

	@XmlElement(name = "xNome")
	private String razaoSocial;

	@XmlElement(name = "IE")
	private String inscricaoEstadual;

	@XmlElement(name = "xEnder")
	private String endereco;

	@XmlElement(name = "xMun")
	private String municipio;

	@XmlElement(name = "UF")
	private String uf;

}
