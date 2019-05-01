package org.iel.code_sismatic.model;

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

// "   date_part('dow', created_on) = " +
// "   date_part('dow', cast(:datetime AS date))
@NamedQueries({
	@NamedQuery(name = "QtdaVezesMaquinaParou.listarTodos", query = "SELECT DISTINCT q FROM tb_qtda_maquina_parou q"),
	@NamedQuery(name = "QtdaVezesMaquinaParou.find", query = "SELECT DISTINCT q FROM tb_qtda_maquina_parou q WHERE q.id = :pId"),
})
@Entity(name = "tb_qtda_maquina_parou")
@XmlRootElement
public class QtdaVezesMaquinaParou implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;

	@Column(name = "power",updatable = false)
	@Min(0) @Max(1)
	@NotNull
	private int power;

	@Column(name = "no_run",updatable = false)
	@NotNull
	@Min(0) @Max(1)
	private int noRun;
	
	@Column(name = "status",updatable = false)
	@NotNull
	@Min(0) @Max(1)
	private int status;
	
	@Column(name = "date_time",updatable = false)
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