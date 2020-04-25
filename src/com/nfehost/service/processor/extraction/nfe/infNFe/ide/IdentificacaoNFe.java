package com.nfehost.service.processor.extraction.nfe.infNFe.ide;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ide")
public class IdentificacaoNFe {
	
	@XmlElement(name = "natOp")
	private String descricaoNaturezaOperacao;

	@XmlElement(name = "serie")
	private Integer serie;

	@XmlElement(name = "nNF")
	private Integer numero;

	@XmlElement(name = "dEmi")
	private Date dataEmissao;

	@XmlElement(name = "dSaiEnt")
	private Date dataHoraEntradaOuSaidaProduto;
	
	@XmlElement(name = "hSaiEn")
	private Date horaSaidaEntrada;

	@XmlElement(name = "tpNF")
	private Integer tipoOperacao;

	@XmlElement(name = "tpEmis")
	private Integer tipoEmissao;

	@XmlElement(name = "finNFe")
	private Integer finalidadeEmissao;
	
	@XmlElement(name = "procEmi")
	private Integer indentificadorProcessoEmissao;

	@XmlElement(name = "tpImp")
	private Integer tipoImpressao;

	@XmlElement(name = "tpAmb")
	private Integer tipoAmbiente;

}
