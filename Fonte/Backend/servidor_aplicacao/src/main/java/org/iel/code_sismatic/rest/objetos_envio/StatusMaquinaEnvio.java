package org.iel.code_sismatic.rest.objetos_envio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.iel.code_sismatic.model.entidades_dimensao.StatusMaquina;

@XmlRootElement
public class StatusMaquinaEnvio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private LocalDateTime dataInicial;
	
	private LocalDateTime dataFinal;
	
	private List<StatusMaquina> dados;
	
	//construtor
	public StatusMaquinaEnvio( LocalDateTime dataInicialRecebida,  LocalDateTime dataFinalRecebida, List<StatusMaquina> dadosRecebidos ) {
		this.dados = new ArrayList<StatusMaquina>();
		this.dados.addAll(dadosRecebidos);
		this.dataInicial = dataInicialRecebida;
		this.dataFinal = dataFinalRecebida;
	}

	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<StatusMaquina> getDados() {
		return dados;
	}

	public void setDados(List<StatusMaquina> dados) {
		this.dados = dados;
	}

}