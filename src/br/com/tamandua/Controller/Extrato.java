package br.com.tamandua.Controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.tamandua.DAO.ExtratoDAO;
import br.com.tamandua.Model.ExtratoModel;

public class Extrato {
	public String trans_extrato(String nroconta, String data_inicio, String data_fim) {
		ExtratoDAO extratodao = new ExtratoDAO();
		String txt = "";
		List<ExtratoModel> dados = extratodao.extrair_extrato_deposito(nroconta, data_inicio, data_fim);
		for (ExtratoModel extratoModel : dados) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date data_nova = null;
			try {
				data_nova = new java.sql.Date(format.parse(extratoModel.getData()).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txt += "Transação: Deposito -- Valor: " + extratoModel.getValor() + " Data: " + data_nova + " Funcionário: " + extratoModel.getCodigo_funcionario() + "<br>";
		}
		List<ExtratoModel> dados_saque = extratodao.extrair_extrato_saque(nroconta);
		for (ExtratoModel extratoModel : dados_saque) {
			txt += "Transação: Saque -- Valor: " + extratoModel.getValor() + " Data: " + extratoModel.getData() + " Funcionário: " + extratoModel.getCodigo_funcionario()  + "<br>";
		}
		return txt;
	}
}
