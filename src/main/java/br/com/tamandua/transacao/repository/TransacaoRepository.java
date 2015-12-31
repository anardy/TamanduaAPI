package br.com.tamandua.transacao.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.tamandua.transacao.entities.ContaEntity;
import br.com.tamandua.transacao.entities.HistTransacaoEntity;
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
		this.salvarHistoricoTransacao(t);
		return true;
	}
	
	public void atualizarSaldoConta(List<ContaEntity> c) {
		em.merge(c.get(0));
	}
	
	public void salvarHistoricoTransacao(Object t) {
		HistTransacaoEntity hist = new HistTransacaoEntity();
		hist.setCodigotransacao(((TransferenciaEntity) t).getCodigo());
		hist.setNroconta(((TransferenciaEntity) t).getNroconta_concedente());
		hist.setTipotransacao(3);
		hist.setValor(((TransferenciaEntity) t).getValor());
		hist.setData(((TransferenciaEntity) t).getData());
		hist.setFator(1);
		em.persist(hist);
		HistTransacaoEntity hist2 = new HistTransacaoEntity();
		hist2.setCodigotransacao(((TransferenciaEntity) t).getCodigo());
		hist2.setNroconta(((TransferenciaEntity) t).getNroconta_beneficiado());
		hist2.setTipotransacao(3);
		hist2.setValor(((TransferenciaEntity) t).getValor());
		hist2.setData(((TransferenciaEntity) t).getData());
		hist2.setFator(2);
		em.persist(hist2);
	}
	
	public List<HistTransacaoEntity> consultarExtrato(Integer nroConta) {
		TypedQuery<HistTransacaoEntity> query = em.createNamedQuery(HistTransacaoEntity.CONSULTAR_EXTRATO, HistTransacaoEntity.class);
		query.setParameter("nroconta", nroConta);
		return query.getResultList();
	}
}
