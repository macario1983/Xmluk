package com.nfehost.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "contato")
public class Contato extends Persistent {

	private static final long serialVersionUID = -1522770947827705519L;

	@NotEmpty
	@Size(max = 60)
	@Column(name = "nome")
	private String nome;

	@NotEmpty
	@Email(message = "Este endereço de e-mail é inválido")
	@Size(max = 60)
	@Column(name = "email")
	private String email;

	@NotEmpty
	@Size(max = 14)
	@Column(name = "telefone")
	private String telefone;

	@OneToOne
	private Emitente emitente;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Emitente getEmitente() {
		return emitente;
	}

	public void setEmitente(Emitente emitente) {
		this.emitente = emitente;
	}

}
