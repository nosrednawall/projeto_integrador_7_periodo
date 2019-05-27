package org.iel.code_sismatic.dao;

import java.math.BigInteger;

/**
 * classe responsável por receber o loop de valores contido no select
 * @author anderson
 *
 */
public class RetornoSomaPowerMaquina {
	
	private BigInteger soma50Porcento;
	private BigInteger soma100Porcento;
	private BigInteger soma75Porcento;
	private BigInteger soma25Porcento;

	// construtor iniciando os valores com zero
	public RetornoSomaPowerMaquina() {
		this.soma50Porcento = BigInteger.valueOf(0);
		this.soma100Porcento = BigInteger.valueOf(0);
		this.soma75Porcento = BigInteger.valueOf(0);
		this.soma25Porcento = BigInteger.valueOf(0);
	}
	
	
	public BigInteger getSoma50Porcento() {
		return soma50Porcento;
	}
	public void setSoma50Porcento(BigInteger soma50Porcento) {
		this.soma50Porcento = soma50Porcento;
	}
	public BigInteger getSoma100Porcento() {
		return soma100Porcento;
	}
	public void setSoma100Porcento(BigInteger soma100Porcento) {
		this.soma100Porcento = soma100Porcento;
	}
	public BigInteger getSoma75Porcento() {
		return soma75Porcento;
	}
	public void setSoma75Porcento(BigInteger soma75Porcento) {
		this.soma75Porcento = soma75Porcento;
	}
	public BigInteger getSoma25Porcento() {
		return soma25Porcento;
	}
	public void setSoma25Porcento(BigInteger soma25Porcento) {
		this.soma25Porcento = soma25Porcento;
	}
}
