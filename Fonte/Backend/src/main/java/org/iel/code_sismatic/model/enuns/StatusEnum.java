package org.iel.code_sismatic.model.enuns;

public enum StatusEnum {
	 INATIVO(0), ATIVO(1), AMBOS(2);

	private final Integer indice;

	private StatusEnum(Integer indice) {
		this.indice = indice;
	}

	public Integer getIndice() {
		return this.indice;
	}

	public StatusEnum getStatusPeloIndice(Integer indice) {
		if (indice == 1) {
			return StatusEnum.ATIVO;
		} else if (indice == 0) {
			return StatusEnum.INATIVO;
		} else if (indice == 2) {
			return StatusEnum.AMBOS;
		}
		return null;
	}
}
