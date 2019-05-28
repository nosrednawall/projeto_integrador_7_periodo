package org.iel.code_sismatic.rest.objetos_envio;

import java.io.Serializable;

public class RelatorioSomaPowerMaquina implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String soma50Porcento;
	private String soma100Porcento;
	private String soma75Porcento;
	private String soma25Porcento;
	
	public String getSoma50Porcento() {
		return soma50Porcento;
	}
	public void setSoma50Porcento(String soma50Porcento) {
		this.soma50Porcento = soma50Porcento;
	}
	public String getSoma100Porcento() {
		return soma100Porcento;
	}
	public void setSoma100Porcento(String soma100Porcento) {
		this.soma100Porcento = soma100Porcento;
	}
	public String getSoma75Porcento() {
		return soma75Porcento;
	}
	public void setSoma75Porcento(String soma75Porcento) {
		this.soma75Porcento = soma75Porcento;
	}
	public String getSoma25Porcento() {
		return soma25Porcento;
	}
	public void setSoma25Porcento(String soma25Porcento) {
		this.soma25Porcento = soma25Porcento;
	}
	

	
}
