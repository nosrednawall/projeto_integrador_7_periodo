package org.iel.code_sismatic.dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
	
	public RetornoSomaStatusLigadoDesligadoMaquina somaStatusLigadoDesligadoPorPeriodo(String dataInicial, String dataLimite) {
		
		RetornoSomaStatusLigadoDesligadoMaquina retorno = new RetornoSomaStatusLigadoDesligadoMaquina();
		
		//select nativo
		Query somaDadosStatusMaquinaPorPeriodoQuery = getEntityManager().createNativeQuery(""
				+ "SELECT " + 
				"    CAST (data AS date) " + 
				"    , COALESCE(COUNT (status) FILTER(WHERE status=1),0) AS status_ligado " + 
				"    , COALESCE(COUNT (status) FILTER(WHERE status=0),0) AS status_desligado " + 
				"FROM " + 
				"    tb_status_maquina " +
				"WHERE" + 
				"    CAST (data AS date) BETWEEN CAST(:pDataInicial AS date) " + 
				"AND" + 
				"    CAST(:pDataLimite AS date) " + 
				"GROUP BY CAST (data AS date);");

		somaDadosStatusMaquinaPorPeriodoQuery.setParameter("pDataInicial", dataInicial.toString());
		somaDadosStatusMaquinaPorPeriodoQuery.setParameter("pDataLimite", dataLimite.toString());
		
		//efetua o select aqui
		@SuppressWarnings("unchecked")
		List<Object[]>retornoSelect = somaDadosStatusMaquinaPorPeriodoQuery.getResultList();
		
		for(Object[] a : retornoSelect) {
			retorno.setTotalLigado(Util.somaBigIntegers((BigInteger) a[1],retorno.getTotalLigado()));
			retorno.setTotalDesligado(Util.somaBigIntegers((BigInteger) a[2],retorno.getTotalDesligado()));
		}
		
		return retorno;
	}
}
