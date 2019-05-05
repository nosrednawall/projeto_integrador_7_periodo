package org.iel.code_sismatic.control;

import javax.inject.Inject;

import org.iel.code_sismatic.dao.StatusMaquinaDao;
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
	
	/** 
	 * método principal onde a partir dele serão salvos tods os dados de BI
	 * @param entity
	 */
	public void salvaDadosMaquinaEmTabelasBI(DadosMaquina entity) {
		salvaStatusMaquina(entity);
	}

	/**
	 * Método salva na tabela QtdaVezesMaquinaParou
	 * @param dadosMaquina
	 */
	private void salvaStatusMaquina(DadosMaquina entity) {
		
		StatusMaquina qtdaVezesMaquinaParou = 
				new StatusMaquina(
				entity.getPower(), 
				entity.getNoRun(), 
				entity.getStatus(), 
				entity.getDateTime()
				);
		//salva  os dados da maquina na tabela
		daoStatusMaquina.save(qtdaVezesMaquinaParou);
	}

}
