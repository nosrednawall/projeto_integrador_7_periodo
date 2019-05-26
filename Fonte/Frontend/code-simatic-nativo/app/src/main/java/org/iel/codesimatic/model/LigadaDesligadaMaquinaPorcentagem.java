package org.iel.codesimatic.model;

import java.io.Serializable;

public class LigadaDesligadaMaquinaPorcentagem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private float somaLigada;
    private float somaDesligada;

    public float getSomaLigada() {
        return somaLigada;
    }

    public void setSomaLigada(float somaLigada) {
        this.somaLigada = somaLigada;
    }
    public float getSomaDesligada() {
        return somaDesligada;
    }

    public void setSomaDesligada(float somaDesligada) {
        this.somaDesligada = somaDesligada;
    }


}
