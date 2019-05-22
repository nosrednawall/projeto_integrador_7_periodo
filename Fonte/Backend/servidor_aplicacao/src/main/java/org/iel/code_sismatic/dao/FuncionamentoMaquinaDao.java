package org.iel.code_sismatic.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.iel.code_sismatic.model.entidades_dimensao.FuncionamentoMaquina;
import org.iel.code_sismatic.util.Util;

/**
 * Classe responsável por prover os dados dos relatórios referentes ao funcionamento da máquina
 * @author anderson
 *
 */
public class FuncionamentoMaquinaDao extends BaseDao<FuncionamentoMaquina> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "code-simatic-persistence-unit")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	/**
	 * Método lista todos os dados de funcionamento da máquina salvos
	 * @param startPosition
	 * @param maxResult
	 * @return
	 */
	public List<FuncionamentoMaquina> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<FuncionamentoMaquina> findAllQuery = getEntityManager().createNamedQuery("FuncionamentoMaquina.listarTodos",
				FuncionamentoMaquina.class);

		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}

	/**
	 * Método retorna a soma de funcionamento da máquina
	 * @param dataInicial
	 * @param dataLimite
	 * @return
	 */
	public RetornoSomaFuncionamentoMaquina somaFuncionamentoPorPeriodo(String dataInicial, String dataLimite) {
		
		RetornoSomaFuncionamentoMaquina retorno = new RetornoSomaFuncionamentoMaquina();
		
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
		
		//adiciona os parametros da query
		somaDadosFuncionamentoMaquinaPorPeriodoQuery.setParameter("pDataInicial", dataInicial.toString());
		somaDadosFuncionamentoMaquinaPorPeriodoQuery.setParameter("pDataLimite", dataLimite.toString());
		
		//efetua o select aqui
		@SuppressWarnings("unchecked")
		List<Object[]>retornoSelect = somaDadosFuncionamentoMaquinaPorPeriodoQuery.getResultList();
		
		for(Object[] a : retornoSelect) {
			retorno.setTotalAutoMan(Util.somaBigIntegers((BigInteger) a[1],retorno.getTotalAutoMan()));
			retorno.setTotalRunCmd(Util.somaBigIntegers((BigInteger) a[2],retorno.getTotalRunCmd()));
		}
		return retorno;
	}	
}
