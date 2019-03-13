package org.iel.projeto_integrador.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.iel.projeto_integrador.model.Usuario;

/**
 * DAO for Usuario
 */
@Stateless
public class UsuarioDao extends BaseDao<Usuario>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(
			unitName = "projeto-integrador-persistence-unit")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Usuario> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Usuario> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.perfil ORDER BY u.id",
						Usuario.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}


}
