package com.nfehost.service.processor.extraction.nfe.infNFe.empresa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class InfoEmpresa {

	@XmlElement(name = "xNome")
	private String razaoSocial;

	@XmlElement(name = "CNPJ")
	private String cnpj;

	@XmlElement(name = "CPF")
	private String cpf;

	@XmlElement(name = "IE")
	private String inscricaoEstadual;

}
