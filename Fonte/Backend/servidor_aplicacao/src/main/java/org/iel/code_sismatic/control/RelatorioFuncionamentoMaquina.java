package org.iel.code_sismatic.control;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.iel.code_sismatic.dao.FuncionamentoMaquinaDao;
import org.iel.code_sismatic.dao.RetornoSomaFuncionamentoMaquina;
import org.iel.code_sismatic.rest.objetos_envio.RelatorioFuncionamentoMaquinaEnvio;
import org.iel.code_sismatic.util.Util;

@Stateless
public class RelatorioFuncionamentoMaquina {
	@Inject
	private FuncionamentoMaquinaDao daoFuncionamentoMaquina;
	
	/**
	 * Retorna os valores em porcentagens
	 * @param dataInicial
	 * @param dataLimite
	 * @return
	 */
	public RelatorioFuncionamentoMaquinaEnvio getRelatorio(String dataInicial, String dataLimite){
		
		RelatorioFuncionamentoMaquinaEnvio envio = new RelatorioFuncionamentoMaquinaEnvio();
		
		RetornoSomaFuncionamentoMaquina dadosFuncionamento = daoFuncionamentoMaquina.somaFuncionamentoPorPeriodo(dataInicial, dataLimite);
		
		float porcentagemAutoMan = Util.retornaPorcentagemRegradeTres(
				Util.somaBigIntegersRetornaFloat(dadosFuncionamento.getTotalAutoMan(), dadosFuncionamento.getTotalRunCmd()),
				dadosFuncionamento.getTotalAutoMan().floatValue());
		
		float porcentagemRunCmd = Util.retornaPorcentagemRegradeTres(
				Util.somaBigIntegersRetornaFloat(dadosFuncionamento.getTotalAutoMan(), dadosFuncionamento.getTotalRunCmd()),
				dadosFuncionamento.getTotalRunCmd().floatValue());
		
		//adiciona os valores já arredondados
		envio.setSomaAutoMan(Float.toString(Util.arredondar(porcentagemAutoMan)));
		envio.setSomaRunCmd(Float.toString(Util.arredondar(porcentagemRunCmd)));		
		return envio;
	}
}
