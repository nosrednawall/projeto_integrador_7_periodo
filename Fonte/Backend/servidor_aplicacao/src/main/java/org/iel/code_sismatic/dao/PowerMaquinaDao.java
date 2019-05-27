package org.iel.code_sismatic.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.iel.code_sismatic.model.entidades_dimensao.PowerMaquina;
import org.iel.code_sismatic.util.Util;

/**
 * Dao responsável por pegar os valores da tabela dimensao power_maquina
 * @author anderson
 *
 */
public class PowerMaquinaDao extends BaseDao<PowerMaquina> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "code-simatic-persistence-unit")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	/**
	 * Método responsável por fazer o select do relatório de quantas vezes a maquina ficou parada nas potencias abaixo
	 * @param dataInicial
	 * @param dataLimite
	 * @return
	 */
	public RetornoSomaPowerMaquina somaPowerMaquina(String dataInicial, String dataLimite) {
		
		RetornoSomaPowerMaquina retorno = new RetornoSomaPowerMaquina();
		
		//select nativo
		Query query = getEntityManager().createNativeQuery(""
				+ "SELECT " + 
				"    CAST (data AS date) " + 
				"    , COALESCE(COUNT (power) FILTER(WHERE power=100),0) AS Cem_Porcento " + 
				"    , COALESCE(COUNT (power) FILTER(WHERE power=75),0) AS Setenta_e_cinco_Porcento " + 
				"    , COALESCE(COUNT (power) FILTER(WHERE power=50),0) AS Cinquenta_Porcento " + 
				"    , COALESCE(COUNT (power) FILTER(WHERE power=25),0) AS Vinte_E_cinco_Porcento " +  
				"FROM " + 
				"    tb_power_maquina " +
				"WHERE" + 
				"    CAST (data AS date) BETWEEN CAST(:pDataInicial AS date) " + 
				"AND" + 
				"    CAST(:pDataLimite AS date) " + 
				"GROUP BY CAST (data AS date);");

		query.setParameter("pDataInicial", dataInicial.toString());
		query.setParameter("pDataLimite", dataLimite.toString());
		
		//efetua o select aqui
		@SuppressWarnings("unchecked")
		List<Object[]>retornoSelect = query.getResultList();
		
		for(Object[] a : retornoSelect) {
			retorno.setSoma100Porcento(Util.somaBigIntegers((BigInteger) a[1],retorno.getSoma100Porcento()));
			retorno.setSoma75Porcento(Util.somaBigIntegers((BigInteger) a[2],retorno.getSoma75Porcento()));
			retorno.setSoma50Porcento(Util.somaBigIntegers((BigInteger) a[3],retorno.getSoma50Porcento()));
			retorno.setSoma25Porcento(Util.somaBigIntegers((BigInteger) a[4],retorno.getSoma25Porcento()));
		}
		
		return retorno;
	}

}
