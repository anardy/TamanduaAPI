package br.com.tamandua.controleacesso.entities;

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
	
	public AcessoEntity() {
	}
	
	public AcessoEntity(String cpf, Integer status, Integer tipo) {
		super();
		this.cpf = cpf;
		this.status = status;
		this.tipo = tipo;
	}

	public static final String BUSCAR_ACESSO = "AcessoEntity.BuscarAcesso";
	
	@Id
	private String cpf;
	private Integer status;
	private Integer tipo;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
}