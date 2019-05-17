package org.iel.code_sismatic.control;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.iel.code_sismatic.dao.FuncionamentoMaquinaDao;
import org.iel.code_sismatic.rest.objetos_envio.RelatorioFuncionamentoMaquinaEnvio;

@Stateless
public class RelatorioFuncionamentoMaquina {
	@Inject
	private FuncionamentoMaquinaDao daoFuncionamentoMaquina;
	
	public RelatorioFuncionamentoMaquinaEnvio getRelatorio(String dataInicial, String dataLimite){
		
		RelatorioFuncionamentoMaquinaEnvio envio = new RelatorioFuncionamentoMaquinaEnvio();
		
		envio.setSomaAutoMan(daoFuncionamentoMaquina.somaFuncionamentoAutomaticoPorPeriodo(dataInicial, dataLimite));
		
		return envio;
	}
}
