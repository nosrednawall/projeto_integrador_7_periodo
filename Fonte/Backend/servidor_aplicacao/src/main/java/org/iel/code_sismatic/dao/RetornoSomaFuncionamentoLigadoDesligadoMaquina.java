package org.iel.code_sismatic.dao;

import java.math.BigInteger;

/**
 * classe resonsável por retornar apenas o objeto do select de funcionamento da máquina
 * @author anderson
 *
 */
public class RetornoSomaFuncionamentoLigadoDesligadoMaquina {
	
	private BigInteger totalLigado;
	private BigInteger totalDesligado;
	
	public RetornoSomaFuncionamentoLigadoDesligadoMaquina(BigInteger totalLigado,BigInteger totalDesligado) {
		this.totalLigado = totalLigado;
		this.totalDesligado = totalDesligado;
	}
	
	public RetornoSomaFuncionamentoLigadoDesligadoMaquina() {
		this.totalLigado = BigInteger.valueOf(0);
		this.totalDesligado = BigInteger.valueOf(0);
	}
	
	public BigInteger getTotalLigado() {
		return totalLigado;
	}
	public void setTotalLigado(BigInteger totalLigado) {
		this.totalLigado = totalLigado;
	}
	public BigInteger getTotalDesligado() {
		return totalDesligado;
	}
	public void setTotalDesligado(BigInteger totalDesligado) {
		this.totalDesligado = totalDesligado;
	}
	
	public String getTotalLigadoToString() {
		return this.totalLigado.toString();
	}
	
	public String getTotalDesligadoToString() {
		return this.totalDesligado.toString();
	}

}
