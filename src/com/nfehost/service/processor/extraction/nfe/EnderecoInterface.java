package com.nfehost.service.processor.extraction.nfe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EnderecoInterface {

	@XmlElement(name = "xLgr")
	private String logradouro;

	@XmlElement(name = "nro")
	private String numero;

	@XmlElement(name = "xCpl")
	private String complemento;

	@XmlElement(name = "xBairro")
	private String bairro;

	@XmlElement(name = "xMun")
	private String municipio;

	@XmlElement(name = "UF")
	private String UF;

	@XmlElement(name = "CEP")
	private String cep;

	@XmlElement(name = "fone")
	private Long telefone;

}
