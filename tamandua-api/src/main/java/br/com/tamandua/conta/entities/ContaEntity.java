package br.com.tamandua.conta.entities;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_conta")
@NamedQueries({
	@NamedQuery(name = ContaEntity.BUSCAR_CONTA, query = "select c from ContaEntity c where c.nroconta = :nroconta"),
	@NamedQuery(name = ContaEntity.BUSCAR_TODAS_CONTA, query = "select c from ContaEntity c")
})
public class ContaEntity {
	
	public static final String BUSCAR_CONTA = "ContaEntity.BuscarConta";
	public static final String BUSCAR_TODAS_CONTA = "ContaEntity.BuscarTodasContas";
	
	@Id
	private Integer nroconta;
	private String correntista;
	private Double saldo;
	private String senha;
	@Transient // pq tá transient????
	private Time dataconta;
	private Integer status;

	public String getCorrentista() {
		return correntista;
	}

	public void setCorrentista(String correntista) {
		this.correntista = correntista;
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
	
	public Time getDataconta() {
		return dataconta;
	}

	public void setDataconta(Time dataconta) {
		this.dataconta = dataconta;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
