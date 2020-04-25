package com.nfehost.service.processor.extraction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nfehost.service.processor.extraction.nfe.NFe;
import com.nfehost.service.processor.extraction.protnfe.ProtNFe;

@XmlRootElement(name = "nfeProc")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeProc {

	@XmlElement(name = "NFe")
	private NFe nfe;

	@XmlElement(name = "protNFe")
	private ProtNFe protNFe;

	public NFe getNfe() {
		return nfe;
	}

	public void setNfe(NFe nfe) {
		this.nfe = nfe;
	}

	public ProtNFe getProtNFe() {
		return protNFe;
	}

	public void setProtNFe(ProtNFe protNFe) {
		this.protNFe = protNFe;
	}

}
