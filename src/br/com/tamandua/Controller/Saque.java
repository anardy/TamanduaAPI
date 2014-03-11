package br.com.tamandua.Controller;

import br.com.tamandua.DAO.ContaDAO;
import br.com.tamandua.DAO.SaqueDAO;
import br.com.tamandua.Model.SaqueModel;

public class Saque extends Transaction {
	
	public String trans_saque(Double valor, String nroconta, String senha) {
		Conta conta = new Conta();
		String result = null;
		if (conta.valida_nroconta(nroconta)) {
			if (conta.valida_senha(nroconta, senha)) {
				if (conta.recupera_saldo(nroconta) > 0) {
					String codigo = getToken();
					SaqueModel saquemodel = new SaqueModel();
					saquemodel.setCodigo(codigo);
					ContaDAO contadao = new ContaDAO();
					Double novo_saldo = conta.calculo_conta_subtract(nroconta, valor);
					if (conta.valida_saldo(novo_saldo)) {
						if (contadao.atualiza_saldo(nroconta, novo_saldo)) {
							SaqueDAO saquedao = new SaqueDAO();
							saquemodel.setValor(valor);
							saquemodel.setNroconta(Integer.parseInt(nroconta));
							saquemodel.setCodfuncionario("321");
							saquedao.inserir_saque(saquemodel);
							
							result = "Transação realizada com sucesso!";
						} else {
							result = "Houve um erro na atualização do saldo na conta.";
						}
					} else {
						result = "Na realização da transação constatou que a conta não possui saldo suficiente para a finalizar a transação";
					}
				} else {
					result = "Sem saldo disponível para realização transação de saque!";
				}
			} else {
				result = "Senha inválida";
			}
		} else {
			result = "Conta não existe";
		}
		return result;
	}
}
