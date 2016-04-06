package br.com.tamandua.controleacesso.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "t_tipoAcesso")
@NamedQueries({
	@NamedQuery(name = TipoAcessoEntity.BUSCAR_TODOS_TIPOACESSO, query = "select c from TipoAcessoEntity c"),
})
public class TipoAcessoEntity {
	
	public static final String BUSCAR_TODOS_TIPOACESSO = "TipoAcessoEntity.BuscarTodosTipoAcesso";
	
	@Id
	private int codigo;
	private String nome;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
