package org.iel.code_sismatic.util;

import javax.inject.Named;

@Named("expressoesRegularesUtil")
public class RegexUtil extends ExpressoesRegularesUtil {

	
	/**
	 * Getters and setters
	 */
	public String getREGEX_EMAIL() {
		return REGEX_EMAIL;
	}

	public String getREGEX_CPFCNPJ() {
		return REGEX_CPFCNPJ;
	}

	public String getREGEX_CPF() {
		return REGEX_CPF;
	}

	public String getREGEX_TELEFONE() {
		return REGEX_TELEFONE;
	}

	public String getREGEX_SENHA() {
		return REGEX_SENHA;
	}

	public String getREGEX_NOME() {
		return REGEX_NOME;
	}

	public String getREGEX_NOME_FORTE() {
		return REGEX_NOME_FORTE;
	}

	public String getREGEX_LOGRADOURO() {
		return REGEX_LOGRADOURO;
	}

	public String getREGEX_NUMERO_CASA() {
		return REGEX_NUMERO_CASA;
	}

	public String getREGEX_BAIRRO() {
		return REGEX_BAIRRO;
	}

	public String getREGEX_MUNICIPIO() {
		return REGEX_MUNICIPIO;
	}

	public String getREGEX_APELIDO() {
		return REGEX_APELIDO;
	}

	public String getREGEX_CEP() {
		return REGEX_CEP;
	}

	public String getREGEX_TELEFONE_SEM_MASCARA() {
		return REGEX_TELEFONE_SEM_MASCARA;
	}
}
