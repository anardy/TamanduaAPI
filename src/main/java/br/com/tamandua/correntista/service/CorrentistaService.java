package br.com.tamandua.correntista.service;

import java.util.List;

import br.com.tamandua.correntista.entities.CorrentistaEntity;

public interface CorrentistaService {

	public List<CorrentistaEntity> buscarCorrentista(String cpf);

	public List<CorrentistaEntity> buscarTodosCorrentistas();

	public String inserirCorrentista(String cpf, String nome, String endereco,
			String telefone);

	public String editarCorrentista(String cpf, String nome, String endereco,
			String telefone);
}
