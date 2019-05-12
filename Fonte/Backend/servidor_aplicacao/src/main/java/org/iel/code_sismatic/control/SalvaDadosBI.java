package org.iel.code_sismatic.control;

import javax.inject.Inject;

import org.iel.code_sismatic.dao.FuncionamentoMaquinaDao;
import org.iel.code_sismatic.dao.PowerMaquinaDao;
import org.iel.code_sismatic.dao.StatusMaquinaDao;
import org.iel.code_sismatic.model.entidades_dimensao.FuncionamentoMaquina;
import org.iel.code_sismatic.model.entidades_dimensao.PowerMaquina;
import org.iel.code_sismatic.model.entidades_dimensao.StatusMaquina;
import org.iel.code_sismatic.model.entidades_fato.DadosMaquina;

/**
 * Classe salva os dados de BI nas tabelas de dimensão
 * @author anderson
 *
 */
public class SalvaDadosBI {
	
	//aqui ficarão cada dao de tabela de dimensão
	@Inject
	private StatusMaquinaDao daoStatusMaquina;
	
	@Inject
	private PowerMaquinaDao daoPowerMaquina;
	
	@Inject
	private FuncionamentoMaquinaDao daoFuncionamentoMaquina;
	
	/** 
	 * método principal onde a partir dele serão salvos tods os dados de BI
	 * @param entity
	 */
	public void salvaDadosMaquinaEmTabelasBI(DadosMaquina entity) {
		salvaStatusMaquina(entity);
		salvaValorPowerMaquina(entity);
		salvaDadosFuncionamentoMaquina(entity);
	}

	/**
	 * Método salva na tabela QtdaVezesMaquinaParou
	 * @param dadosMaquina
	 */
	private void salvaStatusMaquina(DadosMaquina entity) {
		
		StatusMaquina status = 
				new StatusMaquina(
				entity.getPower(), 
				entity.getNoRun(), 
				entity.getStatus(), 
				entity.getData()
				);
		//salva  os dados da maquina na tabela
		daoStatusMaquina.save(status);
	}
	
	private void salvaValorPowerMaquina(DadosMaquina entity) {
		
		PowerMaquina power = new PowerMaquina(
				entity.getSpeedPV(),
				entity.getPower(),
				entity.getData()
				);
		daoPowerMaquina.save(power);
		
	}
	
	private void salvaDadosFuncionamentoMaquina(DadosMaquina entity) {
		
		FuncionamentoMaquina dadosFuncionamento = new FuncionamentoMaquina(
				entity.getSpeedPV(),
				entity.getPower(),
				entity.getAutoMan(),
				entity.getRunCmd(),
				entity.getData()
				);	
		daoFuncionamentoMaquina.save(dadosFuncionamento);
	}

}
