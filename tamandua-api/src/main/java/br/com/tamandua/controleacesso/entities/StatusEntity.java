package br.com.tamandua.controleacesso.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "t_status")
@NamedQueries({
	@NamedQuery(name = StatusEntity.BUSCAR_TODOS_STATUS, query = "select c from StatusEntity c"),
})
public class StatusEntity {
	
	public static final String BUSCAR_TODOS_STATUS = "StatusEntity.BuscarTodosStatus";
	
	@Id
	private int codigo;
	private String status;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
