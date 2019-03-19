package org.iel.code_sismatic.model.enuns;

public enum ErrosValidacaoRestEnum {
	EMAIL_EXISTENTE(10, "O email informado já está cadastrado na base de dados."),
	EMAIL_INVALIDO(11, "O email informado não é válido."),

	TELEFONE_EXISTENTE(20, "O telefone informado já está cadastrado na base de dados."),
	TELEFONE_INVALIDO(21, "O telefone informado não é válido."),

	CPF_CNPJ_EXISTENTE(30, "O Cpf/Cnpj informado já está cadastrado na base de dados."),
	CPF_CNPJ_INVALIDO(31, "O Cpf/Cnpj informado não é válido."),

	TIPO_DADOS_INVALIDOS(40, "Os dados enviados não são válidos.");

	public final int indice;

	ErrosValidacaoRestEnum(int value, String nomeExibicao) {
		this.indice = value;
		this.descricaoErro = nomeExibicao;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	private String descricaoErro;

	public String nomeExibicao() {
		return this.descricaoErro;
	}

	public Integer getIndice() {
		return this.indice;
	}

	public SexoEnum[] getValoresSexo() {
		return SexoEnum.values();
	}

	public static ErrosValidacaoRestEnum[] getListValoresErros() {
		return ErrosValidacaoRestEnum.values();
	}

	public static ErrosValidacaoRestEnum getErroPorNumero(int indiceErro) {
		ErrosValidacaoRestEnum erroRetornavel = ErrosValidacaoRestEnum.TIPO_DADOS_INVALIDOS;
		for (ErrosValidacaoRestEnum erro : ErrosValidacaoRestEnum.values()) {
			if (erro.getIndice() == indiceErro) {
				erroRetornavel = erro;
				break;
			}
		}

		return erroRetornavel;
	}

}
