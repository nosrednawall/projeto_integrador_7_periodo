package org.iel.code_sismatic.model.entidades_fato;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
	@NamedQuery(name = "DadosMaquina.listarTodos", query = "SELECT DISTINCT d FROM tb_dados_maquina d"),
	@NamedQuery(name = "DadosMaquina.find", query = "SELECT DISTINCT d FROM tb_dados_maquina d WHERE d.id = :pId"),
})
@Entity(name = "tb_dados_maquina")
@XmlRootElement
public class DadosMaquina implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;
	
	@Column(name = "speed_pv",updatable = false)
	@NotNull
	@Min(0) @Max(1500)
	private int speedPV;

	@Column(name = "power",updatable = false)
	@Min(0) @Max(100)
	@NotNull
	private int power;

	@Column(name = "no_run",updatable = false)
	@NotNull
	@Min(0)
	private int noRun;

	@Column(name = "auto_man",updatable = false)
	@NotNull
	@Min(0) @Max(1)
	private int autoMan;
	
	@Column(name = "run_cmd",updatable = false)
	@NotNull
	@Min(0) @Max(1)
	private int runCmd;
	
	@Column(name = "status",updatable = false)
	@NotNull
	@Min(0) @Max(1)
	private int status;
	
	@Column(name = "data",updatable = false)
	private LocalDateTime data;
	

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime dateTime) {
		this.data = dateTime;
	}

	public int getSpeedPV() {
		return speedPV;
	}

	public void setSpeedPV(int speedPV) {
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
	
	@Override
	public String toString() {
		String texto = 
				"O SpeedPV é de: "+this.getSpeedPV()+"\n"
				+" o status de Power é de: "+this.getPower()+"\n"
				+" o status de AutoMan é de: "+this.getAutoMan()+"\n"
				+" o status de NoRun é de: "+this.getNoRun()+"\n"
				+" o status de RunCMD é de: "+this.getRunCmd()+"\n"
				+" o status de Status é de: "+this.getStatus()+"\n"
				+" a hora em que ocorreu é: "+this.getData()+"\n";
		
		return texto.toString();
	}

}