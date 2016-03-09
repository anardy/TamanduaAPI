package br.com.tamandua.controleacesso.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.tamandua.controleacesso.entities.AcessoEntity;
import br.com.tamandua.controleacesso.entities.ControleAcessoEntity;
import br.com.tamandua.controleacesso.entities.DesassociarEntity;
import br.com.tamandua.controleacesso.entities.StatusEntity;
import br.com.tamandua.controleacesso.entities.TipoAcessoEntity;
import br.com.tamandua.funcionario.entities.FuncionarioEntity;

@Stateless
public class ControleAcessoRepository {
	@PersistenceContext(unitName="tamandua")
	EntityManager em;

	public List<ControleAcessoEntity> buscarControlesAcessos() {
		String select = "select a.cpf, f.nome as nomeFuncionario, a.status, a.tipo, ta.nome nomeTipo, s.status as nomeStatus "
				+ "from t_acesso a, t_tipoAcesso ta, t_funcionario f, t_status s "
				+ "where a.tipo = ta.codigo and a.cpf = f.cpf and a.status = s.codigo";
		Query query = em.createNativeQuery(select, ControleAcessoEntity.class);
		return query.getResultList();
	}
	
	public List<ControleAcessoEntity> buscarControleAcesso(String cpf) {
		String select = "select a.cpf, f.nome as nomeFuncionario, a.status, a.tipo, ta.nome nomeTipo, s.status as nomeStatus "
				+ "from t_acesso a, t_tipoAcesso ta, t_funcionario f, t_status s "
				+ "where a.tipo = ta.codigo and a.cpf = f.cpf and a.status = s.codigo and a.cpf = :cpf";
		Query query = em.createNativeQuery(select, ControleAcessoEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}
	
	public List<DesassociarEntity> buscarDesassociados() {
		String select = "select f.cpf, f.nome from t_acesso a RIGHT JOIN t_funcionario f ON a.cpf=f.cpf where a.cpf IS NULL";
		Query query = em.createNativeQuery(select, DesassociarEntity.class);
		return query.getResultList();
	}
	
	public List<AcessoEntity> buscarAcesso(String cpf) {
		TypedQuery<AcessoEntity> query = em.createNamedQuery(AcessoEntity.BUSCAR_ACESSO, AcessoEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}
	
	public boolean inserirAcesso(AcessoEntity acesso) {
		em.persist(acesso);
		return true;
	}
	
	public List<DesassociarEntity> buscarDesassociado(String cpf) {
		String select = "select f.cpf, f.nome from t_acesso a RIGHT JOIN t_funcionario f ON a.cpf=f.cpf where a.cpf IS NULL and f.cpf = :cpf";
		Query query = em.createNativeQuery(select, DesassociarEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}
	
	public List<TipoAcessoEntity> buscarTodosTipoAcesso() {
		TypedQuery<TipoAcessoEntity> query = em.createNamedQuery(TipoAcessoEntity.BUSCAR_TODOS_TIPOACESSO, TipoAcessoEntity.class);
		return query.getResultList();
	}
	
	public List<StatusEntity> buscarTodosStatus() {
		TypedQuery<StatusEntity> query = em.createNamedQuery(StatusEntity.BUSCAR_TODOS_STATUS, StatusEntity.class);
		return query.getResultList();
	}
	
	public boolean editarAcesso(AcessoEntity acesso) {
		AcessoEntity a = em.find(AcessoEntity.class, acesso.getCpf());
		a.setStatus(acesso.getStatus());
		a.setTipo(acesso.getTipo());
		em.merge(a);
		return true;
	}
}
