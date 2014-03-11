package br.com.tamandua.tasks;

import br.com.tamandua.Controller.Cambio;
import br.com.tamandua.Controller.Deposito;
import br.com.tamandua.Controller.Extrato;
import br.com.tamandua.Controller.Saldo;
import br.com.tamandua.Controller.Saque;
import br.com.tamandua.Controller.Transferencia;

public class TaskCliente {

	public String tasksaque(String nroconta, Double valor, String senha) {
		Saque saque = new Saque();
		return saque.trans_saque(valor, nroconta, senha);
	}

	public String tasksaldo(String nroconta, String senha) {
		Saldo saldo = new Saldo();
		return saldo.trans_saldo(nroconta, senha);
	}

	public String tasktransferencia(String nroconta_concedente,
			String nroconta_beneficiado, Double valor, String senha_concedente) {
		Transferencia transferencia = new Transferencia();
		return transferencia.trans_transferencia(nroconta_concedente,
				nroconta_beneficiado, valor, senha_concedente);
	}

	public String taskdeposito(String nroconta_beneficiado, Double valor) {
		Deposito deposito = new Deposito();
		return deposito.trans_deposito(nroconta_beneficiado, valor);
	}
	
	public String taskcambio(String tipo, Double valor_tamandua, Double valor_real) {
		Cambio cambio = new Cambio();
		return cambio.trans_cambio(tipo, valor_tamandua, valor_real);
	}
	
	public String taskextrato(String nroconta, String data_inicio, String data_fim) {
		Extrato extrato = new Extrato();
		return extrato.trans_extrato(nroconta, data_inicio, data_fim);
	}
	
	/*public static void main(String args[]) {
		Conta conta = new Conta();
		System.out.print(conta.hash_senha("9090"));
	}*/
}
