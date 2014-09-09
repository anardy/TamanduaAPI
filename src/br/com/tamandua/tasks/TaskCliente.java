package br.com.tamandua.tasks;

import br.com.tamandua.Controller.Cambio;
import br.com.tamandua.Controller.Deposito;
import br.com.tamandua.Controller.Extrato;
import br.com.tamandua.Controller.Login;
import br.com.tamandua.Controller.Saldo;
import br.com.tamandua.Controller.Saque;
import br.com.tamandua.Controller.Transferencia;
import br.com.tamandua.Model.Funcionario;

public class TaskCliente {

	public String tasksaque(String nroconta, Double valor, String senha, String codigoFunc) {
		Saque saque = new Saque();
		return saque.trans_saque(valor, nroconta, senha, codigoFunc);
	}

	public String tasksaldo(String nroconta, String senha) {
		Saldo saldo = new Saldo();
		return saldo.trans_saldo(nroconta, senha);
	}

	public String tasktransferencia(String nroconta_concedente,
			String nroconta_beneficiado, Double valor, String senha_concedente, String codigoFunc) {
		Transferencia transferencia = new Transferencia();
		return transferencia.trans_transferencia(nroconta_concedente,
				nroconta_beneficiado, valor, senha_concedente, codigoFunc);
	}

	public String taskdeposito(String nroconta_beneficiado, Double valor, String codigoFunc) {
		Deposito deposito = new Deposito();
		return deposito.trans_deposito(nroconta_beneficiado, valor, codigoFunc);
	}
	
	public String taskcambio(String tipo, Double valor_tamandua, Double valor_real, String codigoFunc) {
		Cambio cambio = new Cambio();
		return cambio.trans_cambio(tipo, valor_tamandua, valor_real, codigoFunc);
	}
	
	public String taskextrato(String nroconta, String data_inicio, String data_fim) {
		Extrato extrato = new Extrato();
		return extrato.trans_extrato(nroconta, data_inicio, data_fim);
	}
	
	public Boolean tasklogin(String user, String senha) {
		Login login = new Login();
		return login.loginFuncionario(user, senha);
	}
	
	public Funcionario taskdadosFuncionario(String user, String senha) {
		Login login = new Login();
		return login.dadosFuncionario(user, senha);
	}
	
	/*public static void main(String args[]) {
		Conta conta = new Conta();
		System.out.print(conta.hash_senha("9090"));
	}*/
}