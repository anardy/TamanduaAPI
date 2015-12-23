package br.com.tamandua.transacao.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

@Stateless
public class TransacaoRepository {
	@PersistenceUnit(unitName="tamandua")
	EntityManager em;
	
	public String saldo() {
		em.createQuery(arg0)
	}
}
