package br.com.tamandua.correntista.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.tamandua.correntista.entities.CorrentistaEntity;

@Stateless
public class CorrentistaService {
	@PersistenceContext(unitName = "tamandua")
	private EntityManager em;

	public List<CorrentistaEntity> buscarCorrentista(String cpf) {
		TypedQuery<CorrentistaEntity> query = em.createNamedQuery(
				CorrentistaEntity.BUSCAR_CORRENTISTA, CorrentistaEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}

	public List<CorrentistaEntity> buscarTodosCorrentistas() {
		TypedQuery<CorrentistaEntity> query = em.createNamedQuery(
				CorrentistaEntity.BUSCAR_TODOS_CORRENTISTAS,
				CorrentistaEntity.class);
		return query.getResultList();
	}

	public void inserirCorrentista(CorrentistaEntity correntista)
			throws Exception {
		try {
			em.persist(correntista);
		} catch (Exception e) {
			throw e;
		}
	}

	public void editarCorrentista(CorrentistaEntity correntista)
			throws Exception {
		try {
			CorrentistaEntity c = em.find(CorrentistaEntity.class,
					correntista.getCpf());
			c.setNome(correntista.getNome());
			c.setEndereco(correntista.getEndereco());
			c.setTelefone(correntista.getTelefone());
			em.merge(c);
		} catch (Exception e) {
			throw e;
		}
	}
}
