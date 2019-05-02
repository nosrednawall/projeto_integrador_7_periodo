package org.iel.codesimatic.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QtdaVezesMaquinaParou implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private int power;

    private int noRun;

    private int status;

    private LocalDateTime dateTime;

    //construtor vazio
    public QtdaVezesMaquinaParou() {}

    //construtor para salvar os dados
    public QtdaVezesMaquinaParou(int power,int noRun, int status, LocalDateTime data) {
        this.power = power;
        this.noRun = noRun;
        this.status = status;
        this.dateTime = data;
    }

    //construtor para listar os dados
    public QtdaVezesMaquinaParou(Long id ,String data, int noRun,int power, int status) {
        this.id = id;
        this.power = power;
        this.noRun = noRun;
        this.status = status;
        setStringToDateTime(data);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setStringToDateTime(String dateTime) {
        LocalDateTime myDate = LocalDateTime.parse(dateTime);

        this.dateTime = myDate;
    }
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getNoRun() {
        return noRun;
    }

    public void setNoRun(int noRun) {
        this.noRun = noRun;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QtdaVezesMaquinaParou)) {
            return false;
        }
        QtdaVezesMaquinaParou other = (QtdaVezesMaquinaParou) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String texto =
                " o status de Power é de: "+this.getPower()+"\n"
                        +" o status de NoRun é de: "+this.getNoRun()+"\n"
                        +" o status de Status é de: "+this.getStatus()+"\n"
                        +" a hora em que ocorreu é: "+this.getDateTime()+"\n";

        return texto.toString();
    }


}
