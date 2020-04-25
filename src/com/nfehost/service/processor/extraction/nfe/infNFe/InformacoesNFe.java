package com.nfehost.service.processor.extraction.nfe.infNFe;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.cobr.Cobr;
import com.nfehost.service.processor.extraction.nfe.infNFe.dest.Dest;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.Det;
import com.nfehost.service.processor.extraction.nfe.infNFe.emit.Emit;
import com.nfehost.service.processor.extraction.nfe.infNFe.ide.IdentificacaoNFe;
import com.nfehost.service.processor.extraction.nfe.infNFe.total.ValoresTotaisDaNota;
import com.nfehost.service.processor.extraction.nfe.infNFe.transp.Transp;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infNFe")
public class InformacoesNFe {

	@XmlElement(name = "emit")
	private Emit emit;

	@XmlElement(name = "dest")
	private Dest dest;
	
	@XmlElement(name = "det")
	private List<Det> listDet;

	@XmlElement(name = "ide")
	private IdentificacaoNFe identificacaoDaNota;

	@XmlElement(name = "total")
	private ValoresTotaisDaNota valoresTotaisDaNota;
	
	@XmlElement(name = "transp")
	private Transp transp;

	@XmlElement(name = "cobr")
	private Cobr cobr;
	
	@XmlAttribute(name = "Id")
	private String id;

}
