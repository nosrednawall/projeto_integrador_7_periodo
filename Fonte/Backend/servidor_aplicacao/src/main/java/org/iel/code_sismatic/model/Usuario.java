package org.iel.code_sismatic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.iel.code_sismatic.model.enuns.SexoEnum;
import org.iel.code_sismatic.model.enuns.StatusEnum;

@NamedQueries({
	@NamedQuery(name = "Usuario.listarTodos", query = "SELECT DISTINCT u FROM tb_usuario u LEFT JOIN FETCH u.perfil"),
	@NamedQuery(name = "Usuario.find", query = "SELECT DISTINCT u FROM tb_usuario u LEFT JOIN FETCH u.perfil WHERE u.id = :pId"),
})


@Entity(name = "tb_usuario")
@XmlRootElement
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;
	@Version
	@Column(name = "version")
	private int version;

	@Column(length = 15, nullable = false)
	@NotNull
	private String cpf;

	@Column(nullable = false)
	@NotNull
	private String email;

	@Column(nullable = false)
	@NotNull
	private String senha;

	@Column(nullable = false)
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private SexoEnum sexo;

	@Column(nullable = false)
	@NotNull
	private String setor;

	@Column(length = 5, nullable = false)
	@NotNull
	private String ramal;

	@Column
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataCriacao;

	@Column(nullable = false)
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private StatusEnum status;

	@Column(nullable = false)
	@NotNull
	private String nomeCompleto;

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_perfil")
	private Perfil perfil;

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
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (cpf != null && !cpf.trim().isEmpty())
			result += "cpf: " + cpf;
		if (email != null && !email.trim().isEmpty())
			result += ", email: " + email;
		if (senha != null && !senha.trim().isEmpty())
			result += ", senha: " + senha;
		result += ", sexo: " + sexo;
		if (setor != null && !setor.trim().isEmpty())
			result += ", setor: " + setor;
		if (ramal != null && !ramal.trim().isEmpty())
			result += ", ramal: " + ramal;
		result += ", status: " + status;
		if (nomeCompleto != null && !nomeCompleto.trim().isEmpty())
			result += ", nomeCompleto: " + nomeCompleto;
		return result;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}