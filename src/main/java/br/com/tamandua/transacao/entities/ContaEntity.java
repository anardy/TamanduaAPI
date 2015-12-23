package br.com.tamandua.transacao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContaEntity {
	private String cpf;
	@Id
	private Integer nroconta;
	private Double saldo;
	private String senha;
	private Integer status;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getNroconta() {
		return nroconta;
	}

	public void setNroconta(Integer nroconta) {
		this.nroconta = nroconta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
