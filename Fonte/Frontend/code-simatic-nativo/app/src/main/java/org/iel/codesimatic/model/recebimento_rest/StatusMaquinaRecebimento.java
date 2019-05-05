package org.iel.codesimatic.model.recebimento_rest;

import org.iel.codesimatic.model.dimensao.StatusMaquina;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatusMaquinaRecebimento implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime dataInicial;

    private LocalDateTime dataFinal;

    private List<StatusMaquina> dados = new ArrayList<StatusMaquina>();


    //construtor
    public StatusMaquinaRecebimento( LocalDateTime dataInicialRecebida,  LocalDateTime dataFinalRecebida, List<StatusMaquina> dadosRecebidos ) {
        this.dados = new ArrayList<StatusMaquina>();
        this.dados.addAll(dadosRecebidos);
        this.dataInicial = dataInicialRecebida;
        this.dataFinal = dataFinalRecebida;
    }

    public LocalDateTime getDataInicial() { return dataInicial; }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<StatusMaquina> getDados() {
        return dados;
    }

    public void setDados(List<StatusMaquina> dados) {
        this.dados = dados;
    }

    public void adionaDados(List<StatusMaquina> dados) {
        this.dados.addAll(dados);
    }

    @Override
    public String toString() {
        return "Data Inicial: " + getDataInicial().toString()+" \n "+
                "Data Final: "+getDataFinal().toString()+" \n "+
                "Dados: "+getDados().toString();
    }
}
