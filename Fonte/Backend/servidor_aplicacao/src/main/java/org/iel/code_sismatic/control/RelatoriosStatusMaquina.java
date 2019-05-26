package org.iel.code_sismatic.control;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.iel.code_sismatic.dao.RetornoSomaStatusLigadoDesligadoMaquina;
import org.iel.code_sismatic.dao.StatusMaquinaDao;
import org.iel.code_sismatic.rest.objetos_envio.RelatorioStatusLigadoDesligadoMaquinaEnvio;
import org.iel.code_sismatic.util.Util;

@Stateless
public class RelatoriosStatusMaquina {
	
	@Inject
	private StatusMaquinaDao daoStatusMaquina;
	
	public RelatorioStatusLigadoDesligadoMaquinaEnvio getRelatorioStatusLigadoDesligado(String dataInicial, String dataLimite) {
		RelatorioStatusLigadoDesligadoMaquinaEnvio relatorio = new RelatorioStatusLigadoDesligadoMaquinaEnvio();
		
		RetornoSomaStatusLigadoDesligadoMaquina dadosFuncionamento = daoStatusMaquina.somaStatusLigadoDesligadoPorPeriodo(dataInicial, dataLimite);
		
		float porcentagemLigado = Util.retornaPorcentagemRegradeTres(
				Util.somaBigIntegersRetornaFloat(dadosFuncionamento.getTotalLigado(), dadosFuncionamento.getTotalDesligado()),
				dadosFuncionamento.getTotalLigado().floatValue());
		
		float porcentagemDesligado = Util.retornaPorcentagemRegradeTres(
				Util.somaBigIntegersRetornaFloat(dadosFuncionamento.getTotalLigado(), dadosFuncionamento.getTotalDesligado()),
				dadosFuncionamento.getTotalDesligado().floatValue());
		
		relatorio.setSomaLigado(Float.toString(Util.arredondar(porcentagemLigado)));
		relatorio.setSomaDesligado(Float.toString(Util.arredondar(porcentagemDesligado)));
		
		return relatorio;
	}
}
