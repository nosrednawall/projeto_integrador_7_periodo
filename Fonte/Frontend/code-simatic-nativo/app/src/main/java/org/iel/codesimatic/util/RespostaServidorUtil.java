package org.iel.codesimatic.util;


public class RespostaServidorUtil {
    int codigo_resposta;
    boolean funcionou;

    public RespostaServidorUtil(int codigo_resposta, boolean funcionou) {
        this.codigo_resposta = codigo_resposta;
        this.funcionou = funcionou;
    }

    public int getCodigo_resposta() {
        return codigo_resposta;
    }

    public void setCodigo_resposta(int codigo_resposta) {
        this.codigo_resposta = codigo_resposta;
    }

    public boolean isFuncionou() {
        return funcionou;
    }

    public void setFuncionou(boolean funcionou) {
        this.funcionou = funcionou;
    }
}
