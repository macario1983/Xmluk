package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms00.ICMS00;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms10.ICMS10;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms20.ICMS20;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms30.ICMS30;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms40.ICMS40;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms51.ICMS51;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms60.ICMS60;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms70.ICMS70;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.icms90.ICMS90;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ICMS")
public class Icms {

	@XmlElement(name = "ICMS00")
	private ICMS00 icms00;

	@XmlElement(name = "ICMS10")
	private ICMS10 icms10;

	@XmlElement(name = "ICMS20")
	private ICMS20 icms20;

	@XmlElement(name = "ICMS30")
	private ICMS30 icms30;

	@XmlElement(name = "ICMS40")
	private ICMS40 icms40;

	@XmlElement(name = "ICMS51")
	private ICMS51 icms51;

	@XmlElement(name = "ICMS60")
	private ICMS60 icms60;

	@XmlElement(name = "ICMS70")
	private ICMS70 icms70;

	@XmlElement(name = "ICMS90")
	private ICMS90 icms90;

}
