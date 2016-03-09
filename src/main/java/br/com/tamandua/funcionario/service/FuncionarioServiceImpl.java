package br.com.tamandua.funcionario.service;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import br.com.tamandua.funcionario.entities.FuncionarioEntity;
import br.com.tamandua.funcionario.repository.FuncionarioRepository;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {
	
	@EJB
	private FuncionarioRepository repository;
	
	@Override
	public List<FuncionarioEntity> buscarTodosFuncionarios() {
		return repository.buscarTodosFuncionarios();
	}
	
	@Override
	public String inserirFuncionario(String cpf, String nome, String senha) {
		String result = "OK";
		FuncionarioEntity func = new FuncionarioEntity();
		func.setCpf(cpf);
		func.setNome(nome);
		func.setSenha(senha);
		repository.inserirFuncionario(func);
		return result;
	}

	@Override
	public List<FuncionarioEntity> bucarFuncionario(String cpf) {
		return repository.buscarFuncionario(cpf);
	}

	@Override
	public String editarFuncionario(String cpf, String nome, String senha) {
		String result = "OK";
		FuncionarioEntity func = new FuncionarioEntity();
		func.setCpf(cpf);
		func.setNome(nome);
		func.setSenha(senha);
		repository.editarFuncionario(func);
		return result;
	}
}
