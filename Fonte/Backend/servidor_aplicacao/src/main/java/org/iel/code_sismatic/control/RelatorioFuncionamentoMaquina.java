package org.iel.code_sismatic.control;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.iel.code_sismatic.dao.FuncionamentoMaquinaDao;
import org.iel.code_sismatic.dao.RetornoSomaFuncionamentoMaquina;
import org.iel.code_sismatic.rest.objetos_envio.RelatorioFuncionamentoMaquinaEnvio;
import org.iel.code_sismatic.util.Util;

/**
 * Classe responsavel por efetuar os relatorios com os dados salvos das maquinas
 * @author anderson
 *
 */
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
		
		//instancio o objeto de retorno
		RelatorioFuncionamentoMaquinaEnvio relatorio = new RelatorioFuncionamentoMaquinaEnvio();
		
		//recebe os dados salvos da maquina
		RetornoSomaFuncionamentoMaquina dadosFuncionamento = daoFuncionamentoMaquina.somaFuncionamentoPorPeriodo(dataInicial, dataLimite);
		
		//obtem a porcentagem dos dados salvos
		float porcentagemAutoMan = Util.retornaPorcentagemRegradeTres(
				Util.somaBigIntegersRetornaFloat(dadosFuncionamento.getTotalAutoMan(), dadosFuncionamento.getTotalRunCmd()),
				dadosFuncionamento.getTotalAutoMan().floatValue());
		
		float porcentagemRunCmd = Util.retornaPorcentagemRegradeTres(
				Util.somaBigIntegersRetornaFloat(dadosFuncionamento.getTotalAutoMan(), dadosFuncionamento.getTotalRunCmd()),
				dadosFuncionamento.getTotalRunCmd().floatValue());
		
		//adiciona a porcentagem com os valores já arredondados
		relatorio.setSomaAutoMan(Float.toString(Util.arredondar(porcentagemAutoMan)));
		relatorio.setSomaRunCmd(Float.toString(Util.arredondar(porcentagemRunCmd)));
		
		//retorna
		return relatorio;
	}
}
