package br.com.tamandua.funcionario.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.tamandua.funcionario.entities.FuncionarioEntity;

@Stateless
public class FuncionarioService {
	@PersistenceContext(unitName = "tamandua")
	private EntityManager em;

	public List<FuncionarioEntity> buscarFuncionario(String cpf) {
		TypedQuery<FuncionarioEntity> query = em.createNamedQuery(
				FuncionarioEntity.BUSCAR_FUNCIONARIO, FuncionarioEntity.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}

	public List<FuncionarioEntity> buscarTodosFuncionarios() {
		TypedQuery<FuncionarioEntity> query = em.createNamedQuery(
				FuncionarioEntity.BUSCAR_TODOS_FUNCIONARIOS,
				FuncionarioEntity.class);
		return query.getResultList();
	}

	public void inserirFuncionario(FuncionarioEntity func) throws Exception {
		try {
			em.persist(func);
		} catch (Exception e) {
			throw e;
		}
	}

	public void editarFuncionario(FuncionarioEntity func) throws Exception {
		try {
			FuncionarioEntity f = em.find(FuncionarioEntity.class,
					func.getCpf());
			f.setNome(func.getNome());
			f.setSenha(func.getSenha());
			em.merge(f);
		} catch (Exception e) {
			throw e;
		}
	}

}
