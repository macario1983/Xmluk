package com.nfehost.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "emitente")
public class Emitente extends Persistent {

	private static final long serialVersionUID = -130115925159064410L;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dados_empresa_id", referencedColumnName = "id")
	private DadosEmpresa dadosEmpresa;
	
	@Column(name = "nome_fantasia")
	@Size(max = 60)
	private String nomeFantasia;

	@Size(max = 14)
	@Column(name = "inscricao_estadual_substituto_tributario")
	private String inscricaoEstadualSubstitutoTributario;

	@Size(max = 15)
	@Column(name = "inscricao_municipal")
	private String inscricaoMunicipal;

	@Size(max = 7)
	@Column(name = "cnae")
	private String cnae;
	
	@Transient
	List<Contato> listaContatos;
	
	@Transient
	List<NotaFiscal> listaNotasFiscais;

	public DadosEmpresa getDadosEmpresa() {
		return dadosEmpresa;
	}

	public void setDadosEmpresa(DadosEmpresa dadosEmpresa) {
		this.dadosEmpresa = dadosEmpresa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getInscricaoEstadualSubstitutoTributario() {
		return inscricaoEstadualSubstitutoTributario;
	}

	public void setInscricaoEstadualSubstitutoTributario(
			String inscricaoEstadualSubstitutoTributario) {
		this.inscricaoEstadualSubstitutoTributario = inscricaoEstadualSubstitutoTributario;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public List<Contato> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}

	public List<NotaFiscal> getListaNotasFiscais() {
		return listaNotasFiscais;
	}

	public void setListaNotasFiscais(List<NotaFiscal> listaNotasFiscais) {
		this.listaNotasFiscais = listaNotasFiscais;
	}
	
}
