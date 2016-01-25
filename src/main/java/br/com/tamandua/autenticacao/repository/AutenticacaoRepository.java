package br.com.tamandua.autenticacao.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.tamandua.autenticacao.entities.AcessoEntity;
import br.com.tamandua.autenticacao.entities.CorrentistaEntity;
import br.com.tamandua.autenticacao.entities.FuncionarioEntity;
import br.com.tamandua.autenticacao.entities.MenuFuncionarioEntity;

@Stateless
public class AutenticacaoRepository {
	@PersistenceContext(unitName="tamandua")
	EntityManager em;
	
	public List<AcessoEntity> buscarAcesso(String cpf) {
		TypedQuery<AcessoEntity> query = em.createNamedQuery(AcessoEntity.BUSCAR_ACESSO, AcessoEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}
	
	public List<CorrentistaEntity> buscarCorrentista(String cpf) {
		TypedQuery<CorrentistaEntity> query = em.createNamedQuery(CorrentistaEntity.BUSCAR_CORRENTISTA, CorrentistaEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}
	
	public List<FuncionarioEntity> buscarFuncionario(String cpf) {
		TypedQuery<FuncionarioEntity> query = em.createNamedQuery(FuncionarioEntity.BUSCAR_FUNCIONARIO, FuncionarioEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}
	
	public List<FuncionarioEntity> buscarTodosFuncionarios() {
		TypedQuery<FuncionarioEntity> query = em.createNamedQuery(FuncionarioEntity.BUSCAR_TODOS_FUNCIONARIOS, FuncionarioEntity.class);
		return query.getResultList();
	}
	
	public List<MenuFuncionarioEntity> buscarMenuFuncionario(String tipo) {
		String select = "select mf.codigo, mf.nome, mf.link, mf.tipoFunc from t_tipoFuncionario tf, t_menuFuncionario mf where tf.codigo = mf.tipoFunc and tf.nome = :tipo";
		Query query = em.createNativeQuery(select, MenuFuncionarioEntity.class);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}
}
