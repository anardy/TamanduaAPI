package br.com.tamandua.autenticacao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="t_correntista")
@NamedQueries({
	@NamedQuery(name = CorrentistaEntity.BUSCAR_CORRENTISTA, query = "select c from CorrentistaEntity c where c.cpf = :cpf")
})
public class CorrentistaEntity {
	
	public static final String BUSCAR_CORRENTISTA = "CorrentistaEntity.BuscarCorrentista";
	
	@Id
	private String cpf;
	private String endereco;
	private String telefone;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
