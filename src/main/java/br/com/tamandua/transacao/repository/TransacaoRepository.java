package br.com.tamandua.transacao.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.tamandua.transacao.entities.ContaEntity;
import br.com.tamandua.transacao.entities.TransferenciaEntity;

@Stateless
public class TransacaoRepository {
	@PersistenceContext(unitName="tamandua")
	EntityManager em;
	
	public List<ContaEntity> buscarConta(Integer nroconta) {
		TypedQuery<ContaEntity> query = em.createNamedQuery(ContaEntity.BUSCAR_CONTA, ContaEntity.class);
		query.setParameter("nroconta", nroconta);
		return query.getResultList();
	}
	
	public boolean inserirTransferencia(TransferenciaEntity t) {
		em.persist(t);
		return true;
	}
	
	public void atualizarSaldoConta(List<ContaEntity> c) {
		em.merge(c.get(0));
	}
}
