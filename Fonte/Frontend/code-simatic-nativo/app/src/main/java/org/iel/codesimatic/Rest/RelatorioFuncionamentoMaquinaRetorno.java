package org.iel.codesimatic.Rest;

import java.io.Serializable;

public class RelatorioFuncionamentoMaquinaRetorno implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int somaAutoMan;
    private int somaRunCmd;

    public int getSomaAutoMan() {
        return somaAutoMan;
    }
    public void setSomaAutoMan(int somaAutoMan) {
        this.somaAutoMan = somaAutoMan;
    }
    public int getSomaRunCmd() {
        return somaRunCmd;
    }
    public void setSomaRunCmd(int somaRunCmd) {
        this.somaRunCmd = somaRunCmd;
    }

}