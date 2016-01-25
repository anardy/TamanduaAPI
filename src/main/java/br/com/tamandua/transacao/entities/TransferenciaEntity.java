package br.com.tamandua.transacao.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_transferencia")
public class TransferenciaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	private Timestamp data;
	private Double valor;
	private Integer nroconta_concedente;
	private Integer nroconta_beneficiado;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Integer getNroconta_concedente() {
		return nroconta_concedente;
	}

	public void setNroconta_concedente(Integer nroconta_concedente) {
		this.nroconta_concedente = nroconta_concedente;
	}
	
	public Integer getNroconta_beneficiado() {
		return nroconta_beneficiado;
	}

	public void setNroconta_beneficiado(Integer nroconta_beneficiado) {
		this.nroconta_beneficiado = nroconta_beneficiado;
	}

}
