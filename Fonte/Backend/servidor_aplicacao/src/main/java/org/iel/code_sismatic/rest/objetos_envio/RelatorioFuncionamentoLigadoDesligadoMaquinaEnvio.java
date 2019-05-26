package org.iel.code_sismatic.rest.objetos_envio;

import java.io.Serializable;

public class RelatorioFuncionamentoLigadoDesligadoMaquinaEnvio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String somaLigado;
	private String somaDesligado;

	public String getSomaLigado() {
		return somaLigado;
	}

	public void setSomaLigado(String somaLigado) {
		this.somaLigado = somaLigado;
	}

	public String getSomaDesligado() {
		return somaDesligado;
	}

	public void setSomaDesligado(String somaDesligado) {
		this.somaDesligado = somaDesligado;
	}
}
