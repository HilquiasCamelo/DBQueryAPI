package com.hilquiascamelo.dbqueryapi.entity.dto;

import com.hilquiascamelo.dbqueryapi.annotation.CheckEmail;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class UserNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message="Preenchimento obrigatório")
	@CheckEmail (message = "Endereço de email inválido")
	private String email;

	private Integer tipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String password;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String logradouro;

	@NotEmpty(message="Preenchimento obrigatório")
	private String numero;

	@NotEmpty(message="Preenchimento obrigatório")
	private Boolean situation;

	public UserNewDTO() {
	}

	//Creating a constructor with arguments makes it easier to instantiate classes

	public UserNewDTO(String nome, String email, Integer tipo, String password, String logradouro, String numero, Boolean situation) {
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.password = password;
		this.logradouro = logradouro;
		this.numero = numero;
		this.situation = situation;
	}

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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public Boolean getSituation() {
		return situation;
	}

	public void setSituation(Boolean situation) {
		this.situation = situation;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof UserNewDTO)) return false;
		if (!super.equals(object)) return false;
		UserNewDTO that = (UserNewDTO) object;
		return Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(tipo, that.tipo) && Objects.equals(password, that.password) && Objects.equals(logradouro, that.logradouro) && Objects.equals(numero, that.numero);
	}

	public int hashCode() {
		return Objects.hash(super.hashCode(), nome, email, tipo, password, logradouro, numero);
	}
}
