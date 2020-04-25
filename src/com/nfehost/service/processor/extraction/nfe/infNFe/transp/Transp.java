package com.nfehost.service.processor.extraction.nfe.infNFe.transp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

import com.nfehost.service.processor.extraction.nfe.infNFe.transp.transporta.Transporta;
import com.nfehost.service.processor.extraction.nfe.infNFe.transp.veictransp.VeicTransp;
import com.nfehost.service.processor.extraction.nfe.infNFe.transp.vol.Vol;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transp")
public class Transp {
	
	@XmlElement(name = "modFrete")
	private Integer modoFrete;
	
	@XmlElement(name = "transporta")
	private Transporta transporta;
	
	@XmlElement(name = "veicTransp")
	private VeicTransp veiculoTransporte;
	
	@XmlElement(name = "vol")
	private List<Vol> listVolume;

}
