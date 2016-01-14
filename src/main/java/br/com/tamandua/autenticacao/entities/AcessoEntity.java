package br.com.tamandua.autenticacao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="t_acesso")
@NamedQueries({
	@NamedQuery(name = AcessoEntity.BUSCAR_ACESSO, query = "select c from AcessoEntity c where c.cpf = :cpf")
})
public class AcessoEntity {
	
	public static final String BUSCAR_ACESSO = "AcessoEntity.BuscarAcesso";
	
	@Id
	private String cpf;
	private String nome;
	private Integer status;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}