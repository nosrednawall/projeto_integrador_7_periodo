package org.iel.codesimatic.Rest;

import java.io.Serializable;

public class RelatorioFuncionamentoMaquinaRetorno implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private float somaAutoMan;
    private float somaRunCmd;

    public float getSomaAutoMan() {
        return somaAutoMan;
    }

    public void setSomaAutoMan(float somaAutoMan) {
        this.somaAutoMan = somaAutoMan;
    }
    public float getSomaRunCmd() {
        return somaRunCmd;
    }

    public void setSomaRunCmd(float somaRunCmd) {
        this.somaRunCmd = somaRunCmd;
    }

}