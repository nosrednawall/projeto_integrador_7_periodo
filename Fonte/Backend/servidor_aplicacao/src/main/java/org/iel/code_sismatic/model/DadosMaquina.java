package org.iel.code_sismatic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
	@NamedQuery(name = "dadosMaquina.listarTodos", query = "SELECT DISTINCT d FROM tb_dados_maquina d"),
	@NamedQuery(name = "dadosMaquina.find", query = "SELECT DISTINCT d FROM tb_dados_maquina d WHERE d.id = :pId"),
})
@Entity(name = "tb_dados_maquina")
@XmlRootElement
public class DadosMaquina implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;
	
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "speed_pv")
	@NotNull
	private String speedPV;

	@Column(name = "power")
	@NotNull
	private String power;

	@Column(name = "no_run")
	@NotNull
	private String noRun;

	@Column(name = "auto_man")
	@NotNull
	private String autoMan;
	
	@Column(name = "run_cmd")
	@NotNull
	private String runCmd;
	
	@Column(name = "status")
	@NotNull
	private String status;
	
	
	
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

}