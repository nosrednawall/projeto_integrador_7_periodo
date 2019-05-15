package org.iel.code_sismatic.rest.objetos_envio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RelatorioFuncionamentoMaquinaEnvio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String somaAutoMan;
	private String somaRunCmd;
	
	public String getSomaAutoMan() {
		return somaAutoMan;
	}
	public void setSomaAutoMan(String somaAutoMan) {
		this.somaAutoMan = somaAutoMan;
	}
	public String getSomaRunCmd() {
		return somaRunCmd;
	}
	public void setSomaRunCmd(String somaRunCmd) {
		this.somaRunCmd = somaRunCmd;
	}
	
}
