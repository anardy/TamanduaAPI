package br.com.tamandua.transacao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="conta")
@NamedQueries({
	@NamedQuery(name = ContaEntity.BUSCAR_CONTA, query = "select c from ContaEntity c where c.nroconta = :nroconta")
})
public class ContaEntity {
	
	public static final String BUSCAR_CONTA = "ContaEntity.BuscarConta";
	
	private String cliente;
	@Id
	private Integer nroconta;
	private Double saldo;
	private String senha;
	private Integer status;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
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
