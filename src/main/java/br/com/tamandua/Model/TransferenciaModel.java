package br.com.tamandua.Model;

import java.sql.Timestamp;

public class TransferenciaModel {
	private String codigo;
	private Double valor;
	private Timestamp data;
	private int cliente_concedente;
	private int cliente_beneficiado;
	private String codigo_funcionario;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public int getCliente_concedente() {
		return cliente_concedente;
	}

	public void setCliente_concedente(int cliente_concedente) {
		this.cliente_concedente = cliente_concedente;
	}

	public int getCliente_beneficiado() {
		return cliente_beneficiado;
	}

	public void setCliente_beneficiado(int cliente_beneficiado) {
		this.cliente_beneficiado = cliente_beneficiado;
	}

	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}

	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}

}
