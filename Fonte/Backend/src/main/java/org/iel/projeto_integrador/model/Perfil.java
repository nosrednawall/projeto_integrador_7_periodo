package org.iel.projeto_integrador.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.iel.projeto_integrador.model.enuns.StatusEnum;

@NamedQueries({
	@NamedQuery(name = "Perfil.listarTodos", query = "SELECT DISTINCT p FROM tb_perfil p LEFT JOIN FETCH p.permissoes"),
	@NamedQuery(name = "Perfil.find", query = "SELECT DISTINCT p FROM tb_perfil p LEFT JOIN FETCH p.permissoes WHERE p.id = :pId"),
})
@Entity(name = "tb_perfil")
@XmlRootElement
public class Perfil implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;
	
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "nome")
	@NotNull
	private String nome;

	@Column(name = "status")
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private StatusEnum status;


	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Permissao> permissoes = new HashSet<Permissao>();
	
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
		if (!(obj instanceof Perfil)) {
			return false;
		}
		Perfil other = (Perfil) obj;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nome != null && !nome.trim().isEmpty())
			result += "nome: " + nome;
		result += ", status: " + status;
		return result;
	}

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
}