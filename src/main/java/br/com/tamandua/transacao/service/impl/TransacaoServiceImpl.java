package br.com.tamandua.transacao.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import br.com.tamandua.transacao.entities.ContaEntity;
import br.com.tamandua.transacao.entities.TransferenciaEntity;
import br.com.tamandua.transacao.repository.TransacaoRepository;
import br.com.tamandua.transacao.service.TransacaoService;

@ApplicationScoped
public class TransacaoServiceImpl implements TransacaoService {
	@EJB
	private TransacaoRepository transacaoRepository;

	@Override
	public List<ContaEntity> buscarConta(Integer nroConta) {
		return transacaoRepository.buscarConta(nroConta);
	}

	@Override
	public boolean validaSenha(Integer nroConta, String senha) {
		boolean result = false;
		List<ContaEntity> conta = transacaoRepository.buscarConta(nroConta);
		if (conta.get(0).getNroconta().intValue() == nroConta
				&& conta.get(0).getSenha().equals(senha)) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean atualizaSaldoConta(Integer nroConta, Double valor) {
		boolean result = false;
		List<ContaEntity> conta = transacaoRepository.buscarConta(nroConta);
		Double saldo = conta.get(0).getSaldo();
		saldo += valor;
		if (validaSaldoConta(nroConta)) {
			conta.get(0).setSaldo(saldo);
			transacaoRepository.atualizarSaldoConta(conta);
			result = true;
		}
		return result;
	}

	public boolean validaSaldoConta(Integer nroConta) {
		boolean result = false;
		List<ContaEntity> conta = transacaoRepository.buscarConta(nroConta);
		if (conta.get(0).getSaldo() > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public String inserirTransferencia(Integer nrocontaConcedente,
			String senhaConcedente, Double valor, Integer nrocontaBeneficiado) {
		String result = "OK";
		if (validaSenha(nrocontaConcedente, senhaConcedente)) {
			if (validaSaldoConta(nrocontaConcedente)) {
				java.util.Date date = new java.util.Date();
				TransferenciaEntity t = new TransferenciaEntity();
				t.setNroconta_beneficiado(nrocontaBeneficiado);
				t.setNroconta_concedente(nrocontaConcedente);
				t.setData(new Timestamp(date.getTime()));
				t.setValor(valor);
				transacaoRepository.inserirTransferencia(t);
				atualizaSaldoConta(nrocontaBeneficiado, valor);
			} else {
				result = "Sem saldo em conta";
			}
		} else {
			result = "Senha inválida";
		}
		return result;
	}
}
