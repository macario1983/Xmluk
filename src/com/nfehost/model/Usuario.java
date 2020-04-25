package com.nfehost.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;

import org.hibernate.annotations.Type;

import com.nfehost.dao.hibernate.type.UsuarioType;

@Entity
@Table(name = "usuario")
public class Usuario extends Persistent {

	private static final long serialVersionUID = 4834877779437348044L;

	public enum Nivel {

		ADMINISTRADOR('A', "Administrador"), 
		OPERADOR('O', "Operador");
		
		@Getter
 		private final char index;
		@Getter 
		private final String descricao;

		private Nivel(char index, String descricao) {
			this.index = index;
			this.descricao = descricao;
		}

		public static Nivel valueOf(char index) {
			for (Nivel type : Nivel.values()) {
				if (type.index == index) {
					return type;
				}
			}
			return null;
		}

	}

	@NotNull
	@Column(name = "login", unique = true, length = 10)
	private String login;

	@NotNull
	@Column(name = "senha", length = 10)
	private String senha;

	@Type(type = UsuarioType.TYPE)
	@Column(name = "nivel", columnDefinition = "char")
	private Nivel nivel;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

}
