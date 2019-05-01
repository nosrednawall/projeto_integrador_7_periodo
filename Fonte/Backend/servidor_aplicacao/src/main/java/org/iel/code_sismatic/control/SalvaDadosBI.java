package org.iel.code_sismatic.control;

import javax.inject.Inject;

import org.iel.code_sismatic.dao.QtdaVezesMaquinaParouDao;
import org.iel.code_sismatic.model.DadosMaquina;
import org.iel.code_sismatic.model.QtdaVezesMaquinaParou;

/**
 * Classe salva os dados de BI nas tabelas de dimensão
 * @author anderson
 *
 */
public class SalvaDadosBI {
	
	//aqui ficarão cada dao de tabela de dimensão
	@Inject
	private QtdaVezesMaquinaParouDao daoQtdaVezesMaquinaParou;
	
	/** 
	 * método principal onde a partir dele serão salvos tods os dados de BI
	 * @param entity
	 */
	public void salvaDadosMaquinaEmTabelasBI(DadosMaquina entity) {
		salvaQtdaVezesMaquinaParou(entity);
	}

	/**
	 * Método salva na tabela QtdaVezesMaquinaParou
	 * @param dadosMaquina
	 */
	private void salvaQtdaVezesMaquinaParou(DadosMaquina entity) {
		
		if(entity.getNoRun() == 1) {
			QtdaVezesMaquinaParou qtdaVezesMaquinaParou = 
					new QtdaVezesMaquinaParou(
					entity.getPower(), 
					entity.getNoRun(), 
					entity.getStatus(), 
					entity.getDateTime()
					);
			//salva  os dados da maquina na tabela
			daoQtdaVezesMaquinaParou.save(qtdaVezesMaquinaParou);
		}
	}

}
