package br.com.tamandua.autenticacao.service;

import java.util.List;

import br.com.tamandua.autenticacao.entities.MenuFuncionarioEntity;

public interface AutenticaoService {
	public boolean validaCpf(String cpf);
	public String tipoAcesso(String cpf);
	public String autenticaFuncionario(String cpf, String senha);
	public List<MenuFuncionarioEntity> montarMenuFuncionario(String tipo);
	public String validaFuncionario(String cpf);
}
