package org.iel.codesimatic.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DadosMaquina implements Serializable {

    private Long id;
    private static final long serialVersionUID = 1L;

    private int version;

    private String speedPV;

    private int power;

    private int noRun;

    private int autoMan;

    private int runCmd;

    private int status;

    private LocalDateTime dateTime;


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSpeedPV() {
        return speedPV;
    }

    public void setSpeedPV(String speedPV) {
        this.speedPV = speedPV;
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

    public int getAutoMan() {
        return autoMan;
    }

    public void setAutoMan(int autoMan) {
        this.autoMan = autoMan;
    }

    public int getRunCmd() {
        return runCmd;
    }

    public void setRunCmd(int runCmd) {
        this.runCmd = runCmd;
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
        if (!(obj instanceof DadosMaquina)) {
            return false;
        }
        DadosMaquina other = (DadosMaquina) obj;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        String texto =
                "O SpeedPV é de: "+this.getSpeedPV()+"\n"
                        +" o status de Power é de: "+this.getPower()+"\n"
                        +" o status de AutoMan é de: "+this.getAutoMan()+"\n"
                        +" o status de NoRun é de: "+this.getNoRun()+"\n"
                        +" o status de RunCMD é de: "+this.getRunCmd()+"\n"
                        +" o status de Status é de: "+this.getStatus()+"\n"
                        +" a hora em que ocorreu é: "+this.getDateTime()+"\n";

        return texto.toString();
    }

}