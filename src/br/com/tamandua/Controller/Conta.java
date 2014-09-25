package br.com.tamandua.Controller;

import br.com.tamandua.DAO.ContaDAO;
import br.com.tamandua.Model.ContaModel;

public class Conta extends Transaction {

	public boolean valida_senha(String nroconta, String senha) {
		boolean result = false;
		ContaDAO contadoa = new ContaDAO();
		if (contadoa.valida_senha(nroconta, hash_senha(senha))) {
			result = true;
		}
		return result;
	}

	public boolean valida_nroconta(String nroconta) {
		ContaDAO dao = new ContaDAO();
		return dao.valida_nroconta(nroconta) ? true : false;
	}

	public Double recupera_saldo(String nroconta) {
		ContaDAO dao = new ContaDAO();
		return dao.valida_saldo(nroconta);
	}
	
	public boolean valida_saldo(Double novo_saldo) {
		return (novo_saldo >= 0) ? true : false;
	}

	public Double calculo_conta_subtract(String nroconta, Double valor) {
		ContaDAO dao = new ContaDAO();
		return (dao.valida_saldo(nroconta) - valor);
	}

	public Double calculo_conta_soma(String nroconta, Double valor) {
		ContaDAO dao = new ContaDAO();
		return (dao.valida_saldo(nroconta) + valor);
	}

	public String criar_conta(String cpf, String senha) {
		String mensagem = "";
		boolean result = false;

		ContaModel conta = new ContaModel();

		conta.setCpf(cpf);
		conta.setSenha(senha);

		result = new ContaDAO().criar_conta(conta);
		mensagem = (result) ? "Conta gerada com Sucesso!"
				: "Houve um erro ao gerar a Conta";
		return mensagem;
	}
}
