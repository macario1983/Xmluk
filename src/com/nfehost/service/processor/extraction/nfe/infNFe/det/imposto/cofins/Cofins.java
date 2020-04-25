package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins.cofinsaliq.CofinsAliq;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins.cofinsnt.CofinsNt;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins.cofinsoutr.CofinsOutr;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.cofins.cofinsqtde.CofinsQtde;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CONFINS")
public class Cofins {
	
	@XmlElement(name = "COFINSAliq")
	private CofinsAliq cofinsAliq;
	
	@XmlElement(name = "COFINSNT")
	private CofinsNt cofinsNt;
	
	@XmlElement(name = "COFINSOutr")
	private CofinsOutr cofinsOutr;
	
	@XmlElement(name = "COFINSQtde")
	private CofinsQtde cofinsQtde;

}
