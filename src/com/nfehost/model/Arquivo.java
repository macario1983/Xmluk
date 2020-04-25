package com.nfehost.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;


@Entity
@Table(name = "arquivo_nota_fiscal")
public class Arquivo extends Persistent {

	private static final long serialVersionUID = -3255362119912486510L;

	@Size(max = 45)
	@Column(name = "nome")
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_registro", updatable = false)
	private Date dataRegistro;

	@Lob
	//@Basic(fetch = FetchType.LAZY)
	@Column(name = "conteudo", columnDefinition = "longtext")
	private String conteudo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
