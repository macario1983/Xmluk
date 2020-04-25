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
@Table(name = "destinatario")
public class Destinatario extends Persistent {

	private static final long serialVersionUID = -7091318100871934315L;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dados_empresa_id", referencedColumnName = "id")
	private DadosEmpresa dadosEmpresa;

	@Size(max = 9)
	@Column(name = "inscricao_suframa")
	private String inscricaoSuframa;

	@Size(max = 60)
	@Column(name = "email")
	private String email;
	
	@Transient
	private List<NotaFiscal> listaNotasFiscais;

	public DadosEmpresa getDadosEmpresa() {
		return dadosEmpresa;
	}

	public void setDadosEmpresa(DadosEmpresa dadosEmpresa) {
		this.dadosEmpresa = dadosEmpresa;
	}

	public String getInscricaoSuframa() {
		return inscricaoSuframa;
	}

	public void setInscricaoSuframa(String inscricaoSuframa) {
		this.inscricaoSuframa = inscricaoSuframa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<NotaFiscal> getListaNotasFiscais() {
		return listaNotasFiscais;
	}

	public void setListaNotasFiscais(List<NotaFiscal> listaNotasFiscais) {
		this.listaNotasFiscais = listaNotasFiscais;
	}

}
