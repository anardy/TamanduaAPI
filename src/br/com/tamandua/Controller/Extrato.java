package br.com.tamandua.Controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.tamandua.DAO.ExtratoDAO;
import br.com.tamandua.Model.ExtratoModel;

public class Extrato {
	public String trans_extrato(String nroconta, String data_inicio,
			String data_fim) {
		ExtratoDAO extratodao = new ExtratoDAO();
		String txt = "";
		List<ExtratoModel> dados = extratodao.extrair_extrato_deposito(
				nroconta, data_inicio, data_fim);

		for (ExtratoModel extratoModel : dados) {
			Timestamp data = extratoModel.getData();
			SimpleDateFormat formatador = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");
			String dataString = formatador.format(data);
			txt += "Transação: Deposito -- Valor: " + extratoModel.getValor()
					+ " Data: " + dataString + " Funcionário: "
					+ extratoModel.getCodigo_funcionario() + "<br>";
		}

		List<ExtratoModel> dados_saque = extratodao
				.extrair_extrato_saque(nroconta, data_inicio, data_fim);
		for (ExtratoModel extratoModel : dados_saque) {
			Timestamp data = extratoModel.getData();
			SimpleDateFormat formatador = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");
			String dataString = formatador.format(data);
			txt += "Transação: Saque -- Valor: " + extratoModel.getValor()
					+ " Data: " + dataString + " Funcionário: "
					+ extratoModel.getCodigo_funcionario() + "<br>";
		}
		List<ExtratoModel> dados_transferencia_concedente = extratodao
				.extrair_extrato_transferenciaConcedente(nroconta, data_inicio, data_fim);
		for (ExtratoModel extratoModel : dados_transferencia_concedente) {
			Timestamp data = extratoModel.getData();
			SimpleDateFormat formatador = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");
			String dataString = formatador.format(data);
			txt += "Transação: Transação -- Cliente Beneficiado: "
					+ extratoModel.getCliente_beneficiado() + " Valor: "
					+ extratoModel.getValor() + " Data: " + dataString
					+ " Funcionário: " + extratoModel.getCodigo_funcionario()
					+ "<br>";
		}
		List<ExtratoModel> dados_transferencia_beneficiado = extratodao
				.extrair_extrato_transferenciaBeneficiado(nroconta, data_inicio, data_fim);
		for (ExtratoModel extratoModel : dados_transferencia_beneficiado) {
			Timestamp data = extratoModel.getData();
			SimpleDateFormat formatador = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");
			String dataString = formatador.format(data);
			txt += "Transação: Transação -- Cliente Concedente: "
					+ extratoModel.getCliente_concedente() + " Valor: "
					+ extratoModel.getValor() + " Data: " + dataString
					+ " Funcionário: " + extratoModel.getCodigo_funcionario()
					+ "<br>";
		}
		return txt;
	}
}
