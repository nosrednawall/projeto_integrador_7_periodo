package org.iel.code_sismatic.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.iel.code_sismatic.model.QtdaVezesMaquinaParou;

/**
 * DAO for Dados Máquina
 */
@Stateless
public class QtdaVezesMaquinaParouDao extends BaseDao<QtdaVezesMaquinaParou> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "code-simatic-persistence-unit")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<QtdaVezesMaquinaParou> listAll(String startPosition, String maxResult) {
		TypedQuery<QtdaVezesMaquinaParou> findAllQuery = getEntityManager().createNamedQuery("QtdaVezesMaquinaParou.listarTodos",
				QtdaVezesMaquinaParou.class);

		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
