package org.iel.projeto_integrador.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.iel.projeto_integrador.model.Perfil;
import org.iel.projeto_integrador.model.enuns.StatusEnum;

/**
 * DAO for Perfil
 */
@Stateless
public class PerfilDao extends BaseDao<Perfil> {
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

	@Override
    public void save(Perfil entity) {
		entity.setStatus(StatusEnum.ATIVO);
        getEntityManager().persist(entity);
    }

	
	public List<Perfil> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Perfil> findAllQuery = getEntityManager().createNamedQuery("Perfil.listarTodos",Perfil.class);

		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
