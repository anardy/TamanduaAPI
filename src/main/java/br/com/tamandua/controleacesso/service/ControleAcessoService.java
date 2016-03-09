package br.com.tamandua.controleacesso.service;

import java.util.List;

import br.com.tamandua.controleacesso.entities.AcessoEntity;
import br.com.tamandua.controleacesso.entities.ControleAcessoEntity;
import br.com.tamandua.controleacesso.entities.DesassociarEntity;
import br.com.tamandua.controleacesso.entities.StatusEntity;
import br.com.tamandua.controleacesso.entities.TipoAcessoEntity;

public interface ControleAcessoService {
	public List<ControleAcessoEntity> buscaTodosControleAcesso();
	public List<ControleAcessoEntity> buscaControleAcesso(String cpf);
	public String editarAcesso(String cpf, Integer status, Integer tipo);
	public List<DesassociarEntity> buscarDesassociados();
	public List<DesassociarEntity> buscarDesassociado(String cpf);
	public List<TipoAcessoEntity> buscarTodosTipoAcesso();
	public List<StatusEntity> buscarTodosStatus();
	public List<AcessoEntity> buscarAcesso(String cpf);
	public String inserirAcesso(String cpf, Integer status, Integer tipo);

}
