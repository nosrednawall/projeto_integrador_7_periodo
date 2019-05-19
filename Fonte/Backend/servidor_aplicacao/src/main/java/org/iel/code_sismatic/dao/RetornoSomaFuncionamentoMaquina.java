package org.iel.code_sismatic.dao;

import java.math.BigInteger;

/**
 * classe resonsável por retornar apenas o objeto do select de funcionamento da máquina
 * @author anderson
 *
 */
public class RetornoSomaFuncionamentoMaquina {
	
	private BigInteger totalAutoMan;
	private BigInteger totalRunCmd;
	
	public RetornoSomaFuncionamentoMaquina(BigInteger totalAutoMan,BigInteger totalRunCmd) {
		this.totalAutoMan = totalAutoMan;
		this.totalRunCmd = totalRunCmd;
	}
	
	public RetornoSomaFuncionamentoMaquina() {
		this.totalAutoMan = BigInteger.valueOf(0);
		this.totalRunCmd = BigInteger.valueOf(0);
	}
	
	public BigInteger getTotalAutoMan() {
		return totalAutoMan;
	}
	public void setTotalAutoMan(BigInteger totalAutoMan) {
		this.totalAutoMan = totalAutoMan;
	}
	public BigInteger getTotalRunCmd() {
		return totalRunCmd;
	}
	public void setTotalRunCmd(BigInteger totalRunCmd) {
		this.totalRunCmd = totalRunCmd;
	}
	
	public String getTotalAutoManToString() {
		return this.totalAutoMan.toString();
	}
	
	public String getTotalRunCmdToString() {
		return this.totalRunCmd.toString();
	}

}
