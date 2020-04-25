package com.nfehost.service.processor.extraction.nfe.infNFe.cobr;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.cobr.dup.Dup;
import com.nfehost.service.processor.extraction.nfe.infNFe.cobr.fat.Fat;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cobr")
public class Cobr {

	@XmlElement(name = "fat")
	private List<Fat> listFatura;

	@XmlElement(name = "dup")
	private List<Dup> listDuplicata;

}
