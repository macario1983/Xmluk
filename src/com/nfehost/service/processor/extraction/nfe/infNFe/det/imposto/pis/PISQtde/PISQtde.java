package com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.PISQtde;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.PisInterface;

@EqualsAndHashCode(callSuper = false)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PISQtde")
public class PISQtde extends PisInterface {

	@XmlElement(name = "qBCProd")
	private Double quantidadeVendida;

	@XmlElement(name = "vAliqProd")
	private BigDecimal valorAliquotaProduto;
	
	@XmlElement(name = "vPIS")
	private BigDecimal valor;

}
