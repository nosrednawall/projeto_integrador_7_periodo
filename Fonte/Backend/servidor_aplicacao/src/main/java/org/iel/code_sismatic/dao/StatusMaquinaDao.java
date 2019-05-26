package org.iel.code_sismatic.dao;

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
	
	public RetornoSomaFuncionamentoLigadoDesligadoMaquina somaFuncionamentoLigadoDesligadoPorPeriodo(String dataInicial, String dataLimite) {
		
		RetornoSomaFuncionamentoLigadoDesligadoMaquina retorno = new RetornoSomaFuncionamentoLigadoDesligadoMaquina();
		
		
		//select nativo
		Query somaDadosFuncionamentoMaquinaPorPeriodoQuery = getEntityManager().createNativeQuery(""
				+ "SELECT " + 
				"    CAST (data AS date), SUM(auto_man) as soma_auto_man , SUM(run_cmd) as soma_run_cmd " + 
				"FROM " + 
				"    tb_funcionamento_maquina " + 
				"WHERE" + 
				"    CAST (data AS date) BETWEEN CAST(:pDataInicial AS date) " + 
				"AND" + 
				"    CAST(:pDataLimite AS date) " + 
				"GROUP BY CAST (data AS date);");
		
		
		return null;
	}
}
