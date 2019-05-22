package org.iel.codesimatic.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Usuario implements Serializable {

    private Long id;
    private static final long serialVersionUID = 1L;

    private int version;

    private String cpf;

    private String email;

    private String senha;

    private SexoEnum sexo;

    private String setor;

    private String ramal;

    private LocalDateTime dataCriacao;

    private StatusEnum status;

    private String nomeCompleto;

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

    public Integer getSexoInteger() {
        return sexo.ordinal();
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getDataCriacaoToString() {
        return dataCriacao.toString();
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public Integer getStatusInteger() {
        return status.ordinal();
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
}