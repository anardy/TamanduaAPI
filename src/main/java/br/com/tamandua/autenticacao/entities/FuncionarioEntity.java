package br.com.tamandua.autenticacao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "t_funcionario")
@NamedQueries({
	@NamedQuery(name = FuncionarioEntity.BUSCAR_FUNCIONARIO, query = "select c from FuncionarioEntity c where c.cpf = :cpf"),
	@NamedQuery(name = FuncionarioEntity.BUSCAR_TODOS_FUNCIONARIOS, query = "select c from FuncionarioEntity c"),
})
public class FuncionarioEntity {
	
	public static final String BUSCAR_FUNCIONARIO = "FuncionarioEntity.BuscarFuncionario";
	public static final String BUSCAR_TODOS_FUNCIONARIOS = "FuncionarioEntity.BuscarTodosFuncionarios";
	
	@Id
	private String cpf;
	private String senha;
	private Integer tipo;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
}