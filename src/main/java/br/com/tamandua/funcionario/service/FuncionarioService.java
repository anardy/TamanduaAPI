package br.com.tamandua.funcionario.service;

import java.util.List;

import br.com.tamandua.funcionario.entities.FuncionarioEntity;

public interface FuncionarioService {
	public List<FuncionarioEntity> buscarTodosFuncionarios();
	public List<FuncionarioEntity> bucarFuncionario(String cpf);
	public String inserirFuncionario(String cpf, String nome, String senha);

	public String editarFuncionario(String cpf, String nome, String senha);
}
