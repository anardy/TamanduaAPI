package br.com.tamandua.Controller;

public class Saldo {
	
	public String trans_saldo(String nroconta, String senha) {
		Conta conta = new Conta();
		String result = "";
		if (conta.valida_nroconta(nroconta)) {
			if (conta.valida_senha(nroconta, senha)) {
				result = conta.recupera_saldo(nroconta).toString();
			} else {
				result = "Senha inv�lida";
			}
		} else {
			result = "Conta n�o existe";
		}
		return result;
	}
}
