package br.com.tamandua.Controller;

import java.util.ArrayList;
import java.util.List;

import br.com.tamandua.DAO.FuncionarioDAO;
import br.com.tamandua.Model.FuncionarioModel;

public class Funcionario {
	
	public String inserirFuncionario(String cpf, String nome, String login, String senha, short status, String funcao) {
		String mensagem = "";
		boolean result = false;

		FuncionarioModel funcionario = new FuncionarioModel();

		funcionario.setCpf(cpf);
		funcionario.setNome(nome);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setStatus(status);
		funcionario.setFuncao(funcao);

		result = new FuncionarioDAO().inserirFuncionario(funcionario);
		mensagem = (result) ? "Funcionario cadastrado com Sucesso!"
				: "Houve um erro ao cadastrar o funcionario";
		return mensagem;
	}
	
	public String consultarFuncionario(String cpf) {
		String txt = "";
		List<FuncionarioModel> dados = new ArrayList<FuncionarioModel>();
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		dados = funcionariodao.consultarFuncionario(cpf);
		for (FuncionarioModel funcionarioModel : dados) {
			txt += funcionarioModel.getCpf() + ";" + funcionarioModel.getNome() + ";"+funcionarioModel.getLogin()+";"+funcionarioModel.getStatus()+";"+funcionarioModel.getFuncao();
		}
		return txt;
	}
	
	public String removerFuncionario(String cpf) {
		String mensagem = "";
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		boolean result = funcionariodao.removerFuncionario(cpf);
		mensagem = (result) ? "Funcionario removido com Sucesso!"
				: "Houve um erro ao remover o funcionario";
		return mensagem;
	}
	
	public String editarFuncionario(String cpf, String nome, String login, String senha, short status, String funcao) {
		String mensagem = "";
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		
		FuncionarioModel funcionario = new FuncionarioModel();

		funcionario.setCpf(cpf);
		funcionario.setNome(nome);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setStatus(status);
		funcionario.setFuncao(funcao);
		
		boolean result = funcionariodao.editarFuncionario(funcionario);
		mensagem = (result) ? "Funcionario atualizado com Sucesso!"
				: "Houve um erro ao atualizar o funcionario";
		return mensagem;
	}
}
