package br.com.tamandua.autenticacao.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.tamandua.autenticacao.entities.MenuFuncionarioEntity;
import br.com.tamandua.correntista.entities.CorrentistaEntity;

@Stateless
public class AutenticacaoRepository {
	@PersistenceContext(unitName="tamandua")
	EntityManager em;
	
	public List<MenuFuncionarioEntity> buscarMenuFuncionario(String tipo) {
		String select = "select mf.codigo, mf.nome, mf.link, mf.tipoAcesso from t_tipoAcesso tf, t_menuFuncionario mf where tf.codigo = mf.tipoAcesso and tf.nome = :tipo";
		Query query = em.createNativeQuery(select, MenuFuncionarioEntity.class);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}

}
