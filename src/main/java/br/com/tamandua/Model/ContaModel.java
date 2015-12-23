package br.com.tamandua.Model;

public class ContaModel {
	private String cpf;
	private int nroconta;
	private Double saldo;
	private String senha;
	private short status;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getNroconta() {
		return nroconta;
	}

	public void setNroconta(int nroconta) {
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

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}
}
