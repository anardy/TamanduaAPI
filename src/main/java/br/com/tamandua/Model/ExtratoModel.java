package br.com.tamandua.Model;

import java.sql.Timestamp;

public class ExtratoModel {
	private String codigo;
	private int nroconta;
	private Timestamp data;
	private Double valor;
	private String codigo_funcionario;
	private String tipo;
	private String cliente_beneficiado;
	private String cliente_concedente;

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

	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}

	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNroconta() {
		return nroconta;
	}

	public void setNroconta(int nroconta) {
		this.nroconta = nroconta;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}
	
	public String getCliente_beneficiado() {
		return cliente_beneficiado;
	}

	public void setCliente_beneficiado(String cliente_beneficiado) {
		this.cliente_beneficiado = cliente_beneficiado;
	}

	public String getCliente_concedente() {
		return cliente_concedente;
	}

	public void setCliente_concedente(String cliente_concedente) {
		this.cliente_concedente = cliente_concedente;
	}
}
