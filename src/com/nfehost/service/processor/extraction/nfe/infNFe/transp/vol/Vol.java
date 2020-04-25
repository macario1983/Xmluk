package com.nfehost.service.processor.extraction.nfe.infNFe.transp.vol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vol")
public class Vol {

	@XmlElement(name = "qVol")
	private Double volume;

	@XmlElement(name = "esp")
	private String especie;
	
	@XmlElement(name = "marca")
	private Double marca;
	
	@XmlElement(name = "nVol")
	private Double numeracaoVolume;

	@XmlElement(name = "pesoL")
	private Double pesoLiquido;

	@XmlElement(name = "pesoB")
	private Double pesoBruto;

}
