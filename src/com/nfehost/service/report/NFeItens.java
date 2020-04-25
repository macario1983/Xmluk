package com.nfehost.service.report;

import java.math.BigDecimal;

public class NFeItens {

	private String codigo;
	private String nome;
	private Integer Ncm;
	private Integer CST;
	private Integer CFOP;
	private String unidade;
	private Double quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal valorDesconto;
	private BigDecimal valorTotal;
	private BigDecimal BCIcms;
	private BigDecimal valorIcms;
	private BigDecimal valorIpi;
	private Double porcentagemIcms;
	private Double porcentagemIpi;

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getNcm() {
		return Ncm;
	}
	public void setNcm(Integer ncm) {
		Ncm = ncm;
	}
	public Integer getCST() {
		return CST;
	}
	public void setCST(Integer cST) {
		CST = cST;
	}
	public Integer getCFOP() {
		return CFOP;
	}
	public void setCFOP(Integer cFOP) {
		CFOP = cFOP;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getBCIcms() {
		return BCIcms;
	}
	public void setBCIcms(BigDecimal bCIcms) {
		BCIcms = bCIcms;
	}
	public BigDecimal getValorIcms() {
		return valorIcms;
	}
	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}
	public BigDecimal getValorIpi() {
		return valorIpi;
	}
	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}
	public Double getPorcentagemIcms() {
		return porcentagemIcms;
	}
	public void setPorcentagemIcms(Double porcentagemIcms) {
		this.porcentagemIcms = porcentagemIcms;
	}
	public Double getPorcentagemIpi() {
		return porcentagemIpi;
	}
	public void setPorcentagemIpi(Double porcentagemIpi) {
		this.porcentagemIpi = porcentagemIpi;
	}

}
