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
	@NamedQuery(name = "StatusMaquina.listarTodos", query = "SELECT DISTINCT q FROM tb_status_maquina q"),
	@NamedQuery(name = "StatusMaquina.listarTodosComData", query = "SELECT DISTINCT q FROM tb_status_maquina q WHERE q.data >= :pDataInicial"),
	@NamedQuery(name = "StatusMaquina.listarComDataInicialELimite", query = "SELECT DISTINCT q FROM tb_status_maquina q WHERE q.data BETWEEN :pDataInicial AND :pDataLimite"),
	@NamedQuery(name = "StatusMaquina.find", query = "SELECT DISTINCT q FROM tb_status_maquina q WHERE q.id = :pId"),
})
@Entity(name = "tb_status_maquina")
@XmlRootElement
public class StatusMaquina implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;

	@Column(name = "no_run",updatable = false)
	@NotNull
	@Min(0) @Max(1)
	private int noRun;
	
	@Column(name = "status",updatable = false)
	@NotNull
	@Min(0) @Max(1)
	private int status;
	
	@Column(name = "data",updatable = false)
	private LocalDateTime data;
	
	//construtor vazio
	public StatusMaquina() {}

	//construtor para salvar os dados
	public StatusMaquina(int power,int noRun, int status, LocalDateTime data) {
		this.noRun = noRun;
		this.status = status;
		this.data = data;
	}
	
	//construtor para listar os dados
	public StatusMaquina(Long id ,String data, int noRun,int power, int status) {
		this.id = id;
		this.noRun = noRun;
		this.status = status;
		setStringToDateTime(data);
	}
	
	public LocalDateTime getDateTime() {
		return data;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.data = dateTime;
	}
	
	public void setStringToDateTime(String dateTime) {
		LocalDateTime myDate = LocalDateTime.parse(dateTime);
		
		this.data = myDate;
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
		if (!(obj instanceof StatusMaquina)) {
			return false;
		}
		StatusMaquina other = (StatusMaquina) obj;
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
				" o status de NoRun é de: "+this.getNoRun()+"\n"
				+" o status de Status é de: "+this.getStatus()+"\n"
				+" a hora em que ocorreu é: "+this.getDateTime()+"\n";
		
		return texto.toString();
	}

}