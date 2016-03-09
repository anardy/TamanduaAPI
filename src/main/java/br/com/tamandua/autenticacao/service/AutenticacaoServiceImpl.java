package br.com.tamandua.autenticacao.service;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.tamandua.autenticacao.entities.MenuFuncionarioEntity;
import br.com.tamandua.autenticacao.repository.AutenticacaoRepository;
import br.com.tamandua.controleacesso.service.ControleAcessoService;
import br.com.tamandua.correntista.service.CorrentistaService;
import br.com.tamandua.funcionario.repository.FuncionarioRepository;

@ApplicationScoped
public class AutenticacaoServiceImpl implements AutenticaoService {

	@EJB
	private AutenticacaoRepository autenticacaoRepository;

	@EJB
	private FuncionarioRepository funcionarioRepository;

	@Inject
	private ControleAcessoService controleAcessoService;
	
	@Inject
	private CorrentistaService correntistaService;

	@Override
	public boolean validaCpf(String cpf) {
		return (controleAcessoService.buscarAcesso(cpf).size() > 0) ? true
				: false;
	}

	public boolean validaCorrentista(String cpf) {
		return (correntistaService.buscarCorrentista(cpf).size() > 0) ? true
				: false;
	}

	public String validaFuncionario(String cpf) {
		return (funcionarioRepository.buscarFuncionario(cpf).size() > 0) ? converteTipoFuncionario(controleAcessoService
				.buscarAcesso(cpf).get(0).getTipo())
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
		if (validaCorrentista(cpf)) {
			result = "Correntista";
		} else {
			if (validaCpf(cpf)) {
				result = validaFuncionario(cpf);
			} else {
				result = "CPF não existe";
			}
		}
		return result;
	}

	@Override
	public String autenticaFuncionario(String cpf, String senha) {
		return (funcionarioRepository.buscarFuncionario(cpf).get(0).getSenha()
				.equals(senha) ? "Funcionario autenticado" : "Dados inválidos");
	}

	public List<MenuFuncionarioEntity> montarMenuFuncionario(String tipo) {
		return autenticacaoRepository.buscarMenuFuncionario(tipo);
	}
}
