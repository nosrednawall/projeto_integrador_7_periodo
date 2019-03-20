package org.iel.code_sismatic.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.iel.code_sismatic.model.Permissao;

/**
 * DAO for Perfil
 */
@Stateless
public class PermissaoDao extends BaseDao<Permissao> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "code-sismatic-persistence-unit")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Permissao> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Permissao> findAllQuery = getEntityManager().createNamedQuery("Permissao.listarTodos",
				Permissao.class);

		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
