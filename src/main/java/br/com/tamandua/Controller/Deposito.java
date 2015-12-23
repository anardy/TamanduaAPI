package br.com.tamandua.Controller;

import br.com.tamandua.DAO.ContaDAO;
import br.com.tamandua.DAO.DepositoDAO;
import br.com.tamandua.Model.DepositoModel;

public class Deposito extends Transaction {

	public String trans_deposito(String nroconta_beneficiado, Double valor, String codigoFunc) {
		String result = "";
		Conta conta = new Conta();
		ContaDAO contadao = new ContaDAO();
		if (conta.valida_nroconta(nroconta_beneficiado)) {
			DepositoModel deposito = new DepositoModel();
			String codigo = getToken();
			deposito.setCodigo(codigo);
				
			contadao.atualiza_saldo(nroconta_beneficiado, conta.calculo_conta_soma(nroconta_beneficiado, valor));

			deposito.setNroconta(Integer.parseInt(nroconta_beneficiado));
			deposito.setValor(valor);
			deposito.setTipo("cliente");
			deposito.setCodigo_funcionario(codigoFunc);
				
			new DepositoDAO().inserir_deposito(deposito);
			
			result = "Transação de Depósito executada com sucesso";
		} else {
			result = "Conta do beneficiado não existe";
		}
		return result;
	}

}
