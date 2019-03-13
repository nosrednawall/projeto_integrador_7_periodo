package org.iel.projeto_integrador.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

/**
 * @author Anderson
 * 
 * classe base que possui os métodos comuns para todas as Daos 
 *
 * @param <T> classe entity que deverá ser especificada na Dao concreta
 */
public abstract class BaseDao<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//construtor em branco
    public BaseDao() {}

    //Administrador de entidade
    protected abstract EntityManager getEntityManager();

    public void save(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    public void update(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
    }

	public void deleteById(Class<T> type, Object id) {
		T entity = getEntityManager().find(type, id);
		if (entity != null) {
			getEntityManager().remove(entity);
	        getEntityManager().flush();
		}
	}
    
    public T find(Class<T> type, Object id) {
        return getEntityManager().find(type, id);
    }
}
