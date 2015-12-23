package br.com.tamandua.Controller;

import br.com.tamandua.DAO.ContaDAO;
import br.com.tamandua.DAO.TransferenciaDAO;
import br.com.tamandua.Model.TransferenciaModel;

public class Transferencia extends Transaction {

	public String trans_transferencia(String nroconta_concedente,
			String nroconta_beneficiado, Double valor, String senha, String codigoFunc) {
		String result = "";
		Conta conta = new Conta();
		ContaDAO contadao = new ContaDAO();
		if ((conta.valida_nroconta(nroconta_concedente) && (conta
				.valida_nroconta(nroconta_beneficiado)))) {
			if (conta.valida_senha(nroconta_concedente, senha)) {
				TransferenciaModel transferencia = new TransferenciaModel();
				String codigo = getToken();
				transferencia.setCodigo(codigo);
				
				if (conta.recupera_saldo(nroconta_concedente) > 0) {
					Double saldo_novo = conta.calculo_conta_subtract(
							nroconta_concedente, valor);
					if (conta.valida_saldo(saldo_novo)) {
						contadao.atualiza_saldo(nroconta_concedente, saldo_novo);
						contadao.atualiza_saldo(nroconta_beneficiado, conta.calculo_conta_soma(nroconta_beneficiado, valor));
	
						transferencia.setCliente_concedente(Integer.parseInt(nroconta_concedente));
						transferencia.setCliente_beneficiado(Integer.parseInt(nroconta_beneficiado));
						transferencia.setValor(valor);
						transferencia.setCodigo_funcionario(codigoFunc);
						
						TransferenciaDAO transferenciadao = new TransferenciaDAO();
						transferenciadao.inserir_transferencia(transferencia);
						
						result = "Transação de Transferência executada com sucesso";
					} else {
						result = "Na realização da transação constatou que a conta não possui saldo suficiente para a finalizar a transação";
					}
				} else {
					result = "Conta sem saldo para realização transação de transferência!";
				}
			} else {
				result = "Senha inválida";
			}
		} else {
			result = "Conta do concedente ou beneficiado não existe";
		}
		return result;
	}
}
