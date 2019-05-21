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

    public void setSomaAutoMan(String somaAutoMan) {
        this.somaAutoMan = Float.parseFloat(somaAutoMan);
    }
    public float getSomaRunCmd() {
        return somaRunCmd;
    }

    public void setSomaRunCmd(String somaRunCmd) {
        this.somaRunCmd = Float.parseFloat(somaRunCmd);
    }

}