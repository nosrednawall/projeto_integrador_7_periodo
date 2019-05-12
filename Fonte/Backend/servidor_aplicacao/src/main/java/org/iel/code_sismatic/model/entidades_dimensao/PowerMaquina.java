package org.iel.code_sismatic.model.entidades_dimensao;

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
	@NamedQuery(name = "PowerMaquina.listarTodos", query = "SELECT DISTINCT d FROM tb_power_maquina d"),
	@NamedQuery(name = "PowerMaquina.find", query = "SELECT DISTINCT d FROM tb_power_maquina d WHERE d.id = :pId"),
})
@Entity(name = "tb_power_maquina")
@XmlRootElement
public class PowerMaquina implements Serializable {
	
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

	@Column(name = "data",updatable = false)
	private LocalDateTime data;
	

	public PowerMaquina() {}
	
	public PowerMaquina(int speedPV,int power,LocalDateTime data) {
		this.speedPV = speedPV;
		this.power = power;
		this.data = data;
	}
	
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
		if (!(obj instanceof PowerMaquina)) {
			return false;
		}
		PowerMaquina other = (PowerMaquina) obj;
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
				+" a hora em que ocorreu é: "+this.getData()+"\n";
		
		return texto.toString();
	}

}