package br.com.tamandua.controleacesso.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DesassociarEntity {
	@Id
	private String cpf;
	private String nome;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
