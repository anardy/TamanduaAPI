package br.com.tamandua.conta.service;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.tamandua.conta.entities.ContaEntity;
import br.com.tamandua.conta.entities.HistTransacaoEntity;
import br.com.tamandua.conta.entities.TransferenciaEntity;

@Stateless
public class ContaService {
	@PersistenceContext(unitName = "tamandua")
	private EntityManager em;

	public List<ContaEntity> buscarConta(Integer nroconta) {
		TypedQuery<ContaEntity> query = em.createNamedQuery(
				ContaEntity.BUSCAR_CONTA, ContaEntity.class);
		query.setParameter("nroconta", nroconta);
		return query.getResultList();
	}
	
	public List<ContaEntity> buscarContas() {
		TypedQuery<ContaEntity> query = em.createNamedQuery(
				ContaEntity.BUSCAR_TODAS_CONTA, ContaEntity.class);
		return query.getResultList();
	}

	public boolean validaSenha(Integer nroconta, String senha) {
		boolean result = false;
		List<ContaEntity> conta = this.buscarConta(nroconta);
		if (conta.get(0).getNroconta().intValue() == nroconta
				&& conta.get(0).getSenha().equals(senha)) {
			result = true;
		}
		return result;
	}

	public boolean atualizaSaldoConta(Integer nroconta, Double valor) {
		boolean result = false;
		List<ContaEntity> conta = this.buscarConta(nroconta);
		Double saldo = conta.get(0).getSaldo();
		saldo += valor;
		if (validaSaldoConta(nroconta)) {
			conta.get(0).setSaldo(saldo);
			this.atualizarSaldoConta(conta);
			result = true;
		}
		return result;
	}

	public boolean validaSaldoConta(Integer nroconta) {
		boolean result = false;
		List<ContaEntity> conta = this.buscarConta(nroconta);
		if (conta.get(0).getSaldo() > 0) {
			result = true;
		}
		return result;
	}

	public void atualizarSaldoConta(List<ContaEntity> c) {
		em.merge(c.get(0));
	}

	public void salvarHistoricoTransacao(Object t) {
		HistTransacaoEntity hist = new HistTransacaoEntity();
		hist.setCodigotransacao(((TransferenciaEntity) t).getCodigo());
		hist.setNroconta(((TransferenciaEntity) t).getNroconta_concedente());
		hist.setTipotransacao(2);
		hist.setValor(((TransferenciaEntity) t).getValor());
		hist.setData(((TransferenciaEntity) t).getData());
		hist.setFator(1);
		em.persist(hist);
		HistTransacaoEntity hist2 = new HistTransacaoEntity();
		hist2.setCodigotransacao(((TransferenciaEntity) t).getCodigo());
		hist2.setNroconta(((TransferenciaEntity) t).getNroconta_beneficiado());
		hist2.setTipotransacao(2);
		hist2.setValor(((TransferenciaEntity) t).getValor());
		hist2.setData(((TransferenciaEntity) t).getData());
		hist2.setFator(2);
		em.persist(hist2);
	}

	public List<HistTransacaoEntity> consultarExtrato(Integer nroConta) {
		TypedQuery<HistTransacaoEntity> query = em.createNamedQuery(
				HistTransacaoEntity.CONSULTAR_EXTRATO,
				HistTransacaoEntity.class);
		query.setParameter("nroconta", nroConta);
		return query.getResultList();
	}

	public String validaNroConta(Integer nroconta) {
		List<ContaEntity> conta = this.buscarConta(nroconta);
		String result = "OK";
		if (conta.size() <= 0) {
			result = "Dados inválidos";
		}
		if (conta.get(0).getStatus().intValue() != 1) {
			result = "Conta desativada";
		}
		return result;
	}

	public String inserirTransferencia(Integer nrocontaConcedente,
			String senhaConcedente, Double valor, Integer nrocontaBeneficiado) {
		String result = "OK";
		if (nrocontaConcedente.intValue() != nrocontaBeneficiado.intValue()) {
			if (validaSenha(nrocontaConcedente, senhaConcedente)) {
				if (validaSaldoConta(nrocontaConcedente)) {
					TransferenciaEntity t = new TransferenciaEntity();
					t.setNroconta_beneficiado(nrocontaBeneficiado);
					t.setNroconta_concedente(nrocontaConcedente);
					t.setData(new Timestamp(System.currentTimeMillis()));
					t.setValor(valor);
					em.persist(t);
					this.salvarHistoricoTransacao(t);
					atualizaSaldoConta(nrocontaBeneficiado, valor);
				} else {
					result = "Sem saldo em conta";
				}
			} else {
				result = "Senha inválida";
			}
		} else {
			result = "Conta deve ser diferente";
		}
		return result;
	}

	// Isso tem que ir no autentica
	public String autentica(Integer nroconta, String senha) {
		String result = validaNroConta(nroconta);
		if (result.equals("OK")) {
			List<ContaEntity> conta = this.buscarConta(nroconta);
			result = (conta.get(0).getSenha().equals(senha)) ? "Cliente autenticado"
					: "Dados inválidos";
		}
		return result;
	}
}
