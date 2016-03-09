package br.com.tamandua.funcionario.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.tamandua.funcionario.entities.FuncionarioEntity;

@Stateless
public class FuncionarioRepository {
	@PersistenceContext(unitName="tamandua")
	EntityManager em;
	
	public List<FuncionarioEntity> buscarFuncionario(String cpf) {
		TypedQuery<FuncionarioEntity> query = em.createNamedQuery(FuncionarioEntity.BUSCAR_FUNCIONARIO, FuncionarioEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}
	
	public List<FuncionarioEntity> buscarTodosFuncionarios() {
		TypedQuery<FuncionarioEntity> query = em.createNamedQuery(FuncionarioEntity.BUSCAR_TODOS_FUNCIONARIOS, FuncionarioEntity.class);
		return query.getResultList();
	}
	
	public boolean inserirFuncionario(FuncionarioEntity func) {
		em.persist(func);
		return true;
	}
	
	public boolean editarFuncionario(FuncionarioEntity func) {
		FuncionarioEntity f = em.find(FuncionarioEntity.class, func.getCpf());
		f.setNome(func.getNome());
		f.setSenha(func.getSenha());
		em.merge(f);
		return true;
	}
	
}
