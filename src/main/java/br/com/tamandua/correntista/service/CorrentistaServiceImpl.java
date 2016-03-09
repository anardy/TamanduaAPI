package br.com.tamandua.correntista.service;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import br.com.tamandua.correntista.entities.CorrentistaEntity;
import br.com.tamandua.correntista.repository.CorrentistaRepository;

@ApplicationScoped
public class CorrentistaServiceImpl implements CorrentistaService {

	@EJB
	private CorrentistaRepository correntistaRepository;
	
	@Override
	public List<CorrentistaEntity> buscarCorrentista(String cpf) {
		return correntistaRepository.buscarCorrentista(cpf);
	}

	@Override
	public List<CorrentistaEntity> buscarTodosCorrentistas() {
		return correntistaRepository.buscarTodosCorrentistas();
	}

	@Override
	public String inserirCorrentista(String cpf, String nome, String endereco, String telefone) {
		CorrentistaEntity correntista = new CorrentistaEntity();
		String result = "OK";
		correntista.setCpf(cpf);
		correntista.setNome(nome);
		correntista.setEndereco(endereco);
		correntista.setTelefone(telefone);
		correntistaRepository.inserirCorreentista(correntista);
		return result;
	}

	@Override
	public String editarCorrentista(String cpf, String nome, String endereco,
			String telefone) {
		String result = "OK";
		CorrentistaEntity correntista = new CorrentistaEntity();
		correntista.setCpf(cpf);
		correntista.setNome(nome);
		correntista.setEndereco(endereco);
		correntista.setTelefone(telefone);
		correntistaRepository.editarCorrentista(correntista);
		return result;
	}

}
