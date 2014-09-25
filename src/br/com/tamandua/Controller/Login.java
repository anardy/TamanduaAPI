package br.com.tamandua.Controller;

import br.com.tamandua.DAO.LoginDAO;
import br.com.tamandua.Model.FuncionarioModel;

public class Login {
	
	public boolean loginFuncionario(String login, String senha) {
		FuncionarioModel funcionario = new FuncionarioModel();
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		
		return new LoginDAO().autorizadorUsuario(funcionario);
	}
	
	public FuncionarioModel dadosFuncionario(String login, String senha) {
		FuncionarioModel funcionario = new FuncionarioModel();
		
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		
		funcionario = new LoginDAO().verificarTipoFuncionario(funcionario);
		return funcionario;
	}
	
	public boolean verificaLoginAtiva(String login) {
		FuncionarioModel funcionario = new FuncionarioModel();
		
		funcionario.setLogin(login);
		
		return new LoginDAO().verificarLoginAtivo(funcionario);
	}
}

