package com.nfehost.service.processor.extraction.nfe.infNFe.emit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.service.processor.extraction.nfe.infNFe.emit.enderemit.EnderEmit;
import com.nfehost.service.processor.extraction.nfe.infNFe.empresa.InfoEmpresa;

@EqualsAndHashCode(callSuper = false)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emit")
public class Emit extends InfoEmpresa {

	@XmlElement(name = "xFant")
	private String nomeFantasia;

	@XmlElement(name = "IEST")
	private String inscricaoEstadualSubstitutoTributario;

	@XmlElement(name = "IM")
	private String inscricaoMunicipal;

	@XmlElement(name = "CNAE")
	private String CNAE;

	@XmlElement(name = "enderEmit")
	private EnderEmit endereco;

}
