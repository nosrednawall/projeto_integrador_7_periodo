package org.iel.code_sismatic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	public List<QtdaVezesMaquinaParou> buscaLista(String dataInicio, String dataFim) {
		
		List<QtdaVezesMaquinaParou> retorno = new ArrayList<QtdaVezesMaquinaParou>();
		
		Query q = getEntityManager().createNativeQuery(
				"SELECT * "+ 
				"FROM " +
					"tb_qtda_maquina_parou as q " +
				"WHERE " +
				 	"q.date_time >= cast(:pDataInicio) " +
				"AND " +
					"q.date_time <= cast(:pDataFim)", QtdaVezesMaquinaParou.class)
				.setParameter("pDataInicio", dataInicio)
				.setParameter("pDataFim", dataFim);
		
		List<Object[]> listaQtdaVezesMaquinaProu = q.getResultList();
		
		//isso foi muito chato
		for(Object[] objetoArray : listaQtdaVezesMaquinaProu) {
			QtdaVezesMaquinaParou qtda = new QtdaVezesMaquinaParou(
					(Long)objetoArray[0], 
					(String)objetoArray[1], 
					(Integer)objetoArray[2],
					(Integer)objetoArray[3],
					(Integer)objetoArray[4]
					);
			retorno.add(qtda);
		}
		
		return retorno;
	}
}
