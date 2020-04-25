package com.nfehost.service.processor.extraction.nfe.infNFe.total;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.total.icmstot.ICMSTotal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "total")
public class ValoresTotaisDaNota {

	@XmlElement(name = "ICMSTot")
	private ICMSTotal icmsTotal;

}
