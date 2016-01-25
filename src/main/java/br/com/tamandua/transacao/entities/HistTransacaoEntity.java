package br.com.tamandua.transacao.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "t_histtransacao")
@NamedQueries({
	@NamedQuery(name = HistTransacaoEntity.CONSULTAR_EXTRATO, query = "select c from HistTransacaoEntity c where c.nroconta = :nroconta")
})
public class HistTransacaoEntity {
	public static final String CONSULTAR_EXTRATO = "HistTransacaoEntity.ConsultarExtrato";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigohist;
	private Integer codigotransacao;
	private Integer nroconta;
	private Integer tipotransacao;
	private Double valor;
	private Timestamp data;
	private Integer fator;

	public Integer getCodigohist() {
		return codigohist;
	}

	public void setCodigohist(Integer codigohist) {
		this.codigohist = codigohist;
	}

	public Integer getCodigotransacao() {
		return codigotransacao;
	}

	public void setCodigotransacao(Integer codigotransacao) {
		this.codigotransacao = codigotransacao;
	}

	public Integer getNroconta() {
		return nroconta;
	}

	public void setNroconta(Integer nroconta) {
		this.nroconta = nroconta;
	}

	public Integer getTipotransacao() {
		return tipotransacao;
	}

	public void setTipotransacao(Integer tipotransacao) {
		this.tipotransacao = tipotransacao;
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

	public Integer getFator() {
		return fator;
	}

	public void setFator(Integer fator) {
		this.fator = fator;
	}

}
