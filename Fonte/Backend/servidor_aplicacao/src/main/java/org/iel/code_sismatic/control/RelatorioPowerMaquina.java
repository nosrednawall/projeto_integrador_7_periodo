package org.iel.code_sismatic.control;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.iel.code_sismatic.dao.PowerMaquinaDao;
import org.iel.code_sismatic.dao.RetornoSomaPowerMaquina;
import org.iel.code_sismatic.rest.objetos_envio.RelatorioSomaPowerMaquina;
import org.iel.code_sismatic.util.Util;

@Stateless
public class RelatorioPowerMaquina {

	@Inject
	private PowerMaquinaDao daoPower;
	
	
	public RelatorioSomaPowerMaquina getRelatorioSomaPowerMaquina(String dataInicial, String dataLimite) {
		RelatorioSomaPowerMaquina relatorio = new RelatorioSomaPowerMaquina();
		
		RetornoSomaPowerMaquina dadosPower = daoPower.somaPowerMaquina(dataInicial, dataLimite);
		
		float valorTotal = Util.somaBigIntegersRetornaFloat(dadosPower.getSoma50Porcento(), dadosPower.getSoma25Porcento()) + Util.somaBigIntegersRetornaFloat(dadosPower.getSoma100Porcento(), dadosPower.getSoma75Porcento());
		
		float resultado100Porcento = Util.retornaPorcentagemRegradeTres(valorTotal, dadosPower.getSoma100Porcento().floatValue());
		float resultado75Porcento = Util.retornaPorcentagemRegradeTres(valorTotal, dadosPower.getSoma75Porcento().floatValue());
		float resultado50Porcento = Util.retornaPorcentagemRegradeTres(valorTotal, dadosPower.getSoma50Porcento().floatValue());
		float resultado25Porcento = Util.retornaPorcentagemRegradeTres(valorTotal, dadosPower.getSoma25Porcento().floatValue());
		
		relatorio.setSoma100Porcento(Float.toString(Util.arredondar(resultado100Porcento)));
		relatorio.setSoma75Porcento(Float.toString(Util.arredondar(resultado75Porcento)));
		relatorio.setSoma50Porcento(Float.toString(Util.arredondar(resultado50Porcento)));
		relatorio.setSoma25Porcento(Float.toString(Util.arredondar(resultado25Porcento)));
		
		return relatorio;
	}
}
