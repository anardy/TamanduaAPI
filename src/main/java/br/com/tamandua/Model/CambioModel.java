package br.com.tamandua.Model;

import java.sql.Timestamp;

public class CambioModel {
	private String codigo;
	private String tipo;
	private Double valor_tamandua;
	private Double valor_real;
	private Timestamp data;
	private String codigo_funcionario;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor_tamandua() {
		return valor_tamandua;
	}

	public void setValor_tamandua(Double valor_tamandua) {
		this.valor_tamandua = valor_tamandua;
	}

	public Double getValor_real() {
		return valor_real;
	}

	public void setValor_real(Double valor_real) {
		this.valor_real = valor_real;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}

	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}

}
