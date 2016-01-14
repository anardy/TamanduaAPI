package br.com.tamandua.autenticacao.service;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import br.com.tamandua.autenticacao.entities.FuncionarioEntity;
import br.com.tamandua.autenticacao.entities.MenuFuncionarioEntity;
import br.com.tamandua.autenticacao.repository.AutenticacaoRepository;

@ApplicationScoped
public class AutenticacaoServiceImpl implements AutenticaoService {

	@EJB
	private AutenticacaoRepository autenticacaoRepository;

	@Override
	public boolean validaCpf(String cpf) {
		return (autenticacaoRepository.buscarAcesso(cpf).size() > 0) ? true
				: false;
	}

	public boolean validaCorrentista(String cpf) {
		return (autenticacaoRepository.buscarCorrentista(cpf).size() > 0) ? true
				: false;
	}

	public String validaFuncionario(String cpf) {
		return (autenticacaoRepository.buscarFuncionario(cpf).size() > 0) ? converteTipoFuncionario(autenticacaoRepository
				.buscarFuncionario(cpf).get(0).getTipo())
				: "Não encontrou o funcionario";
	}

	public String converteTipoFuncionario(Integer codigo) {
		String tipo = "";
		switch (codigo) {
		case 1:
			tipo = "gerente";
			break;
		case 2:
			tipo = "funcionario";
			break;
		case 3:
			tipo = "adminsistema";
			break;
		default:
			break;
		}
		return tipo;
	}

	@Override
	public String tipoAcesso(String cpf) {
		String result = "";
		if (validaCpf(cpf)) {
			if (validaCorrentista(cpf)) {
				result = "Correntista";
			} else {
				result = validaFuncionario(cpf);
			}
		} else {
			result = "CPF não existe";
		}
		return result;
	}

	@Override
	public String autenticaFuncionario(String cpf, String senha) {
		return (autenticacaoRepository.buscarFuncionario(cpf).get(0).getSenha()
				.equals(senha) ? "Funcionario autenticado" : "Dados inválidos");
	}

	public List<MenuFuncionarioEntity> montarMenuFuncionario(String tipo) {
		return autenticacaoRepository.buscarMenuFuncionario(tipo);
	}

	@Override
	public List<FuncionarioEntity> buscarTodosFuncionarios() {
		return autenticacaoRepository.buscarTodosFuncionarios();
	}
	
	
}
