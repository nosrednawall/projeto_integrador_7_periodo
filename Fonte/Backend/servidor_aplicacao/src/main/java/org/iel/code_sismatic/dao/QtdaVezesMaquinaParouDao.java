package org.iel.code_sismatic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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
		
		String query = "SELECT * "+ 
						"FROM " +
							"tb_qtda_maquina_parou as q " +
						"WHERE " +
							"q.date_time > ?1" +
						"AND " +
							"q.date_time < ?2" +
						"ORDER BY q.id DESC";
		
		TemporalType dataInicioTemporalType = TemporalType.valueOf(dataInicio);
		TemporalType dataFimTemporalType = TemporalType.valueOf(dataInicio);
		
		System.out.println("inicio " + dataInicioTemporalType);
		System.out.println("fim " + dataFimTemporalType);
		
		List<QtdaVezesMaquinaParou> retorno = new ArrayList<QtdaVezesMaquinaParou>();
		
		Query q = getEntityManager().createNativeQuery(
				query, QtdaVezesMaquinaParou.class)
				.setParameter(1, dataInicio)
				.setParameter(2, dataFim);
		
		@SuppressWarnings("unchecked")
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
	
//	public List<QtdaVezesMaquinaParou> buscaLista(String dataInicio, String dataFim) {
//		
//		String query = "SELECT * "+ 
//						"FROM " +
//							"tb_qtda_maquina_parou as q " +
//						"WHERE " +
//							"date_part('dow', q.date_time >= cast(:pDataInicio)) " +
//						"AND " +
//							"date_part('dow', q.date_time <= cast(:pDataFim))";
//		
//		List<QtdaVezesMaquinaParou> retorno = new ArrayList<QtdaVezesMaquinaParou>();
//		
//		Query q = getEntityManager().createNativeQuery(
//				query, QtdaVezesMaquinaParou.class)
//				.setParameter("pDataInicio", dataInicio)
//				.setParameter("pDataFim", dataFim);
//		
//		List<Object[]> listaQtdaVezesMaquinaProu = q.getResultList();
//		
//		//isso foi muito chato
//		for(Object[] objetoArray : listaQtdaVezesMaquinaProu) {
//			QtdaVezesMaquinaParou qtda = new QtdaVezesMaquinaParou(
//					(Long)objetoArray[0], 
//					(String)objetoArray[1], 
//					(Integer)objetoArray[2],
//					(Integer)objetoArray[3],
//					(Integer)objetoArray[4]
//					);
//			retorno.add(qtda);
//		}
//		
//		return retorno;
//	}
}
