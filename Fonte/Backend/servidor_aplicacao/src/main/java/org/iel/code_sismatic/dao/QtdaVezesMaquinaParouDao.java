package org.iel.code_sismatic.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.iel.code_sismatic.model.QtdaVezesMaquinaParou;
import org.iel.code_sismatic.util.Util;

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

	public List<QtdaVezesMaquinaParou> listarDadosApartirDataInicial(String dataInicial) {
		TypedQuery<QtdaVezesMaquinaParou> findAllQuery;
		
		findAllQuery = getEntityManager()
				.createNamedQuery
				("QtdaVezesMaquinaParou.listarTodosComData",QtdaVezesMaquinaParou.class);
		
		findAllQuery.setParameter("pDataInicial", Util.converteStringEmData(dataInicial));
			
		return findAllQuery.getResultList();
	}
	
	//http://0.0.0.0:8080/code-simatic/rest/dados-maquina/maquina-parou?data_inicial=2019-04-01&data_limite=2019-05-02
	public List<QtdaVezesMaquinaParou> listarDadosComDataInicialELimite(String dataInicial, String dataLimite) {
		
		TypedQuery<QtdaVezesMaquinaParou> retorno;
		
		retorno = getEntityManager()
				.createNamedQuery
				("QtdaVezesMaquinaParou.listarComDataInicialELimite",QtdaVezesMaquinaParou.class);
		
		retorno.setParameter("pDataInicial", Util.converteStringEmData(dataInicial));
		retorno.setParameter("pDataLimite", Util.converteStringEmData(dataLimite));
		
		return retorno.getResultList();
	}
}
