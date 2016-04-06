package br.com.tamandua.autenticacao.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.tamandua.autenticacao.entities.MenuFuncionarioEntity;
import br.com.tamandua.controleacesso.service.ControleAcessoService;
import br.com.tamandua.correntista.service.CorrentistaService;
import br.com.tamandua.funcionario.service.FuncionarioService;

@Stateless
public class AutenticacaoService {
	@PersistenceContext(unitName="tamandua")
	private EntityManager em;
	
	@EJB
	private ControleAcessoService controleAcessoService;
	
	@EJB
	private FuncionarioService funcionarioService;
	
	@EJB
	private CorrentistaService correntistaService;
	
	public boolean validaCpf(String cpf) {
		return (controleAcessoService.buscarAcesso(cpf).size() > 0) ? true
				: false;
	}

	public boolean validaCorrentista(String cpf) {
		return (correntistaService.buscarCorrentista(cpf).size() > 0) ? true
				: false;
	}

	public String validaFuncionario(String cpf) {
		return (funcionarioService.buscarFuncionario(cpf).size() > 0) ? converteTipoFuncionario(controleAcessoService
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

	public String autenticaFuncionario(String cpf, String senha) {
		return (funcionarioService.buscarFuncionario(cpf).get(0).getSenha()
				.equals(senha) ? "Funcionario autenticado" : "Dados inválidos");
	}
	
	public List<MenuFuncionarioEntity> buscarMenuFuncionario(String tipo) {
		String select = "select mf.codigo, mf.nome, mf.link, mf.tipoAcesso from t_tipoAcesso tf, t_menuFuncionario mf where tf.codigo = mf.tipoAcesso and tf.nome = :tipo";
		Query query = em.createNativeQuery(select, MenuFuncionarioEntity.class);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}

}
