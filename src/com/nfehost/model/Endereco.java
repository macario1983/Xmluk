package com.nfehost.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

import org.hibernate.annotations.Type;

import com.nfehost.dao.hibernate.type.UFType;

@Entity
@Table(name = "endereco")
public class Endereco extends Persistent {

	private static final long serialVersionUID = -3308931308130690090L;

	public enum UF {

		AC("AC", "Acre"), 
		AL("AL", "Alagoas"), 
		AP("AP", "Amapá"), 
		AM("AM", "Amazonas"), 
		BA("BA", "Bahia"), 
		CE("CE", "Ceara"), 
		DF("DF", "Distrito Federal"), 
		ES("ES", "Espirito Santo"), 
		GO("GO", "Goiás"), 
		MA("MA", "Maranhão"), 
		MT("MT", "Mato Grosso"), 
		MS("MS", "Mato Grosso do Sul"), 
		MG("MG", "Minas Gerais"), 
		PA("PA", "Pará"), 
		PB("PB", "Paraíba"), 
		PR("PR", "Paraná"), 
		PE("PE", "Pernambuco"), 
		PI("PI", "Piauí"), 
		RJ("RJ", "Rio de Janeiro"), 
		RN("RN", "Rio Grande do Norte"), 
		RS("RS", "Rio Grande do Sul"), 
		RO("RO", "Rondônia"), 
		RR("RR", "Roraima"), 
		SC("SC", "Santa Catarina"), 
		SP("SP", "São Paulo"), 
		SE("SE", "Sergipe"), 
		TO("TO", "Tocantins");

		@Getter
		private final String index;
		@Getter 
		private final String descricao;

		private UF(String index, String descricao) {
			this.index = index;
			this.descricao = descricao;
		}

	}

	@NotNull
	@Size(max = 60)
	@Column(name = "logradouro")
	private String logradouro;

	@NotNull
	@Size(max = 60)
	@Column(name = "numero")
	private String numero;

	@Size(max = 60)
	@Column(name = "complemento")
	private String complemento;

	@NotNull
	@Size(max = 60)
	@Column(name = "bairro")
	private String bairro;

	@NotNull
	@Size(max = 60)
	@Column(name = "municipio")
	private String municipio;

	@NotNull
	@Type(type = UFType.TYPE)
	@Column(name = "uf", columnDefinition = "varchar", length = 2)
	private UF uf;

	@NotNull
	@Size(max = 8)
	@Column(name = "cep")
	private String cep;

	@Column(name = "telefone")
	private Long telefone;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

}
