package br.com.tamandua.correntista.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="t_correntista")
@NamedQueries({
	@NamedQuery(name = CorrentistaEntity.BUSCAR_CORRENTISTA, query = "select c from CorrentistaEntity c where c.cpf = :cpf"),
	@NamedQuery(name = CorrentistaEntity.BUSCAR_TODOS_CORRENTISTAS, query = "select c from CorrentistaEntity c")
})
public class CorrentistaEntity {
	
	public static final String BUSCAR_CORRENTISTA = "CorrentistaEntity.BuscarCorrentista";
	public static final String BUSCAR_TODOS_CORRENTISTAS = "CorrentistaEntity.BuscarTodosCorrentistas";
	
	@Id
	private String cpf;
	private String nome;
	private String endereco;
	private String telefone;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
