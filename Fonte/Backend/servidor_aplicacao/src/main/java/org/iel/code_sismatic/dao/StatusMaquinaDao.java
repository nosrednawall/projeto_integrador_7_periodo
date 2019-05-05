package org.iel.code_sismatic.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.iel.code_sismatic.model.entidades_dimensao.StatusMaquina;
import org.iel.code_sismatic.util.Util;

/**
 * DAO for Dados Máquina
 */
@Stateless
public class StatusMaquinaDao extends BaseDao<StatusMaquina> {
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

	public List<StatusMaquina> listarDadosApartirDataInicial(String dataInicial) {
		TypedQuery<StatusMaquina> findAllQuery;
		
		findAllQuery = getEntityManager()
				.createNamedQuery
				("StatusMaquina.listarTodosComData",StatusMaquina.class);
		
		findAllQuery.setParameter("pDataInicial", Util.converteStringEmData(dataInicial));
			
		return findAllQuery.getResultList();
	}
	
	//http://0.0.0.0:8080/code-simatic/rest/dados-maquina/maquina-parou?data_inicial=2019-04-01&data_limite=2019-05-02
	public List<StatusMaquina> listarDadosComDataInicialELimite(String dataInicial, String dataLimite) {
		
		TypedQuery<StatusMaquina> retorno;
		
		retorno = getEntityManager()
				.createNamedQuery
				("StatusMaquina.listarComDataInicialELimite",StatusMaquina.class);
		
		retorno.setParameter("pDataInicial", Util.converteStringEmData(dataInicial));
		retorno.setParameter("pDataLimite", Util.converteStringEmData(dataLimite));
		
		return retorno.getResultList();
	}
}
