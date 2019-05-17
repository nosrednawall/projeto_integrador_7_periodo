package org.iel.code_sismatic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

//	public String somaFuncionamentoAutomaticoPorPeriodo(String dataInicial, String dataLimite) {
//		TypedQuery<FuncionamentoMaquina> findAllQuery;
//		
//		findAllQuery = getEntityManager()
//				.createNamedQuery
//				("FuncionamentoMaquina.somaAutoManPorPeriodo",FuncionamentoMaquina.class);
//		
//		findAllQuery.setParameter("pDataInicial", Util.converteStringEmData(dataInicial));
//		findAllQuery.setParameter("pDataLimite", Util.converteStringEmData(dataLimite));
//		
//		List<Long> retorno = new ArrayList<>();
//		retorno = findAllQuery.getResultList();
//		
//		for(Long valor : retorno) {
//			System.out.println(valor);
//		}
//		
//		
//		
//		return "retorno padrao";
//	}
//	
}
