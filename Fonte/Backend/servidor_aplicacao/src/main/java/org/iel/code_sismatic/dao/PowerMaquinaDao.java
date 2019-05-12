package org.iel.code_sismatic.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.iel.code_sismatic.model.entidades_dimensao.PowerMaquina;

public class PowerMaquinaDao extends BaseDao<PowerMaquina> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "code-simatic-persistence-unit")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	//todo implementar o resto dos métodos

}
