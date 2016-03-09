package br.com.tamandua.controleacesso.service;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import br.com.tamandua.controleacesso.entities.AcessoEntity;
import br.com.tamandua.controleacesso.entities.ControleAcessoEntity;
import br.com.tamandua.controleacesso.entities.DesassociarEntity;
import br.com.tamandua.controleacesso.entities.StatusEntity;
import br.com.tamandua.controleacesso.entities.TipoAcessoEntity;
import br.com.tamandua.controleacesso.repository.ControleAcessoRepository;

@ApplicationScoped
public class ControleAcessoServiceImpl implements ControleAcessoService {

	@EJB
	private ControleAcessoRepository controleAcessoRepository;
	
	@Override
	public List<ControleAcessoEntity> buscaTodosControleAcesso() {
		return controleAcessoRepository.buscarControlesAcessos();
	}
	
	@Override
	public List<ControleAcessoEntity> buscaControleAcesso(String cpf) {
		return controleAcessoRepository.buscarControleAcesso(cpf);
	}

	@Override
	public List<DesassociarEntity> buscarDesassociados() {
		return controleAcessoRepository.buscarDesassociados();
	}

	@Override
	public List<DesassociarEntity> buscarDesassociado(String cpf) {
		return controleAcessoRepository.buscarDesassociado(cpf);
	}

	@Override
	public List<TipoAcessoEntity> buscarTodosTipoAcesso() {
		return controleAcessoRepository.buscarTodosTipoAcesso();
	}

	@Override
	public List<StatusEntity> buscarTodosStatus() {
		return controleAcessoRepository.buscarTodosStatus();
	}

	@Override
	public List<AcessoEntity> buscarAcesso(String cpf) {
		return controleAcessoRepository.buscarAcesso(cpf);
	}

	@Override
	public String inserirAcesso(String cpf, Integer status, Integer tipo) {
		String result = "OK";
		AcessoEntity acesso = new AcessoEntity();
		acesso.setCpf(cpf);
		acesso.setStatus(status);
		acesso.setTipo(tipo);
		controleAcessoRepository.inserirAcesso(acesso);
		return result;
	}

	@Override
	public String editarAcesso(String cpf,
			Integer status, Integer tipo) {
		String result = "OK";
		AcessoEntity acesso = new AcessoEntity();
		acesso.setCpf(cpf);
		acesso.setStatus(status);
		acesso.setTipo(tipo);
		controleAcessoRepository.editarAcesso(acesso);
		return result;
	}
}
