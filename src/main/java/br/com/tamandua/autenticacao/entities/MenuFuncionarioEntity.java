package br.com.tamandua.autenticacao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_menuFuncionario")
public class MenuFuncionarioEntity {
	public static final String BUSCAR_MENU_FUNCIONARIO = "FuncionarioEntity.BuscarMenuFuncionario";

	@Id
	private Integer codigo;
	private String nome;
	private String link;
	private Integer tipoFunc;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getTipoFunc() {
		return tipoFunc;
	}

	public void setTipoFunc(Integer tipoFunc) {
		this.tipoFunc = tipoFunc;
	}

}
