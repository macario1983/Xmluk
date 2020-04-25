package com.nfehost.service.processor.extraction.nfe.infNFe.dest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.service.processor.extraction.nfe.infNFe.dest.enderdest.EnderDest;
import com.nfehost.service.processor.extraction.nfe.infNFe.empresa.InfoEmpresa;

@EqualsAndHashCode(callSuper = false)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dest")
public class Dest extends InfoEmpresa {

	@XmlElement(name = "ISUF")
	private String inscricaoSuframa;

	@XmlElement(name = "e-mail")
	private String email;
	
	@XmlElement(name = "enderDest")
	private EnderDest endereco;

}
