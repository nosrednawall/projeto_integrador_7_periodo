package org.iel.codesimatic.model;

import java.io.Serializable;

public class PowerMaquinaPorcentagem implements Serializable {

    private float soma50Porcento;
    private float soma100Porcento;
    private float soma75Porcento;
    private float soma25Porcento;

    public float getSoma50Porcento() {
        return soma50Porcento;
    }

    public void setSoma50Porcento(float soma50Porcento) {
        this.soma50Porcento = soma50Porcento;
    }

    public float getSoma100Porcento() {
        return soma100Porcento;
    }

    public void setSoma100Porcento(float soma100Porcento) {
        this.soma100Porcento = soma100Porcento;
    }

    public float getSoma75Porcento() {
        return soma75Porcento;
    }

    public void setSoma75Porcento(float soma75Porcento) {
        this.soma75Porcento = soma75Porcento;
    }

    public float getSoma25Porcento() {
        return soma25Porcento;
    }

    public void setSoma25Porcento(float soma25Porcento) {
        this.soma25Porcento = soma25Porcento;
    }
}
