package org.iel.code_sismatic.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.iel.code_sismatic.model.entidades_dimensao.FuncionamentoMaquina;

public class FuncionamentoMaquinaDao extends BaseDao<FuncionamentoMaquina> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "code-simatic-persistence-unit")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
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
	
	//todo implementar o resto dos métodos

	public String somaFuncionamentoAutomaticoPorPeriodo(String dataInicial, String dataLimite) {
			
		Query autoManQuery = getEntityManager().createNativeQuery(""
				+ "SELECT " + 
				"    CAST (data AS date), SUM(auto_man) as soma " + 
				"FROM " + 
				"    tb_funcionamento_maquina " + 
				"WHERE" + 
				"    CAST (data AS date) BETWEEN '2019-04-01' " + 
				"AND" + 
				"    '2019-06-03' " + 
				"GROUP BY CAST (data AS date);");
		
		@SuppressWarnings("unchecked")
		List<Object[]>listagemAutoMan = autoManQuery.getResultList();
		
		Long valor = Long.valueOf("0");
		BigInteger valor2 = BigInteger.valueOf(valor);
		for(Object[] a : listagemAutoMan) {
			System.out.println(a[1]);
			valor2 = (BigInteger) a[1];
		}
		return valor2.toString();
	}	
}
