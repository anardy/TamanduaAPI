package br.com.tamandua.Controller;

import br.com.tamandua.DAO.LoginDAO;
import br.com.tamandua.Model.Funcionario;

public class Login {
	
	public boolean loginFuncionario(String login, String senha) {
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		
		LoginDAO dao = new LoginDAO();
		return dao.autorizadorUsuario(funcionario);
	}
	
	public Funcionario dadosFuncionario(String login, String senha) {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		
		LoginDAO dao = new LoginDAO();
		funcionario = dao.verificarTipoFuncionario(funcionario);
		return funcionario;
		
	}
}

