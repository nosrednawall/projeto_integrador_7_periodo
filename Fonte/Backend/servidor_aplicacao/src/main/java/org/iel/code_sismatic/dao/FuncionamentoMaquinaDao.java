package org.iel.code_sismatic.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.iel.code_sismatic.model.entidades_dimensao.FuncionamentoMaquina;

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
		
		//select nativo
		Query autoManQuery = getEntityManager().createNativeQuery(""
				+ "SELECT " + 
				"    CAST (data AS date), SUM(auto_man) as soma_auto_man , SUM(run_cmd) as soma_run_cmd " + 
				"FROM " + 
				"    tb_funcionamento_maquina " + 
				"WHERE" + 
				"    CAST (data AS date) BETWEEN '2019-04-01' " + 
				"AND" + 
				"    '2019-06-03' " + 
				"GROUP BY CAST (data AS date);");
		
		//efetua o select aqui
		@SuppressWarnings("unchecked")
		List<Object[]>listagemAutoMan = autoManQuery.getResultList();
		
		//instancia as variáveils que receberao os valores do select
		Long valor = Long.valueOf("0");
		BigInteger total_auto_man = BigInteger.valueOf(valor);
		BigInteger total_run_cmd = BigInteger.valueOf(valor);
		
		for(Object[] a : listagemAutoMan) {
			total_auto_man = (BigInteger) a[1];
			total_run_cmd = (BigInteger) a[2];
			System.out.println("auto_man: "+total_auto_man);
			System.out.println("run_cmd: "+total_run_cmd);
		}
		return new RetornoSomaFuncionamentoMaquina(total_auto_man, total_run_cmd);
	}	
}
