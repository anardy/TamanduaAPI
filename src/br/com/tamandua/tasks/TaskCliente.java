package br.com.tamandua.tasks;

import br.com.tamandua.Controller.Cambio;
import br.com.tamandua.Controller.Cliente;
import br.com.tamandua.Controller.Conta;
import br.com.tamandua.Controller.Deposito;
import br.com.tamandua.Controller.Extrato;
import br.com.tamandua.Controller.Funcionario;
import br.com.tamandua.Controller.Login;
import br.com.tamandua.Controller.Saldo;
import br.com.tamandua.Controller.Saque;
import br.com.tamandua.Controller.Transferencia;
import br.com.tamandua.Model.FuncionarioModel;

public class TaskCliente {

	public String tasksaque(String nroconta, Double valor, String senha, String codigoFunc) {
		return new Saque().trans_saque(valor, nroconta, senha, codigoFunc);
	}

	public String tasksaldo(String nroconta, String senha) {
		return new Saldo().trans_saldo(nroconta, senha);
	}

	public String tasktransferencia(String nroconta_concedente,
			String nroconta_beneficiado, Double valor, String senha_concedente, String codigoFunc) {
		return new Transferencia().trans_transferencia(nroconta_concedente,
				nroconta_beneficiado, valor, senha_concedente, codigoFunc);
	}

	public String taskdeposito(String nroconta_beneficiado, Double valor, String codigoFunc) {
		return new Deposito().trans_deposito(nroconta_beneficiado, valor, codigoFunc);
	}
	
	public String taskcambio(String tipo, Double valor_tamandua, Double valor_real, String codigoFunc) {
		return new Cambio().trans_cambio(tipo, valor_tamandua, valor_real, codigoFunc);
	}
	
	public String taskextrato(String nroconta, String data_inicio, String data_fim) {
		return new Extrato().trans_extrato(nroconta, data_inicio, data_fim);
	}
	
	public Boolean tasklogin(String user, String senha) {
		return new Login().loginFuncionario(user, senha);
	}
	
	public FuncionarioModel taskdadosFuncionario(String user, String senha) {
		return new Login().dadosFuncionario(user, senha);
	}
	
	public boolean taskverificaLoginAtivo(String user) {
		return new Login().verificaLoginAtiva(user);
	}
	
	public String taskconsultaCliente(String cpf) {
		return new Cliente().consultarCliente(cpf);
	}
	
	public String taskinserirCliente(String cpf, String nome, String telefone, String endereco, short status) {
		return new Cliente().inserirCliente(cpf, nome, endereco, telefone, status);
	}
	
	public String taskremoverCliente(String cpf) {
		return new Cliente().removerCliente(cpf);
	}
	
	public String taskeditarCliente(String cpf, String nome, String telefone, String endereco, short status) {
		return new Cliente().editarCliente(cpf, nome, endereco, telefone, status);
	}
	
	public String taskconsultaFuncionario(String cpf) {
		return new Funcionario().consultarFuncionario(cpf);
	}
	
	public String taskinserirFuncionario(String cpf, String nome, String login, String senha, short status, String funcao) {
		return new Funcionario().inserirFuncionario(cpf, nome, login, senha, status, funcao);
	}
	
	public String taskremoverFuncionario(String cpf) {
		return new Funcionario().removerFuncionario(cpf);
	}
	
	public String taskeditarFuncionario(String cpf, String nome, String login, String senha, short status, String funcao) {
		return new Funcionario().editarFuncionario(cpf, nome, login, senha, status, funcao);
	}
	
	public String taskcriarConta(String cpf, String senha) {
		return new Conta().criar_conta(cpf, senha);
	}

}