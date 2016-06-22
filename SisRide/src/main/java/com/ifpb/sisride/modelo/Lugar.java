package com.ifpb.sisride.modelo;

import java.util.Objects;

public class Lugar {

    private String descricao;
    private String nome;
    private int identificacao;
    private String rua;
    private String cidade;
    private String estado;
    private int numero;
    private String emailUsuario;

    public Lugar(String descricao, String nome, String rua,
            String cidade, String estado, int numero, String emailUsuario) {
        this.descricao = descricao;
        this.nome = nome;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.emailUsuario = emailUsuario;
    }

    public Lugar(String descricao, String nome, String rua, String cidade,
            String estado, int numero, String emailUsuario, int identificacao) {
        this.descricao = descricao;
        this.nome = nome;
        this.identificacao = identificacao;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.emailUsuario = emailUsuario;
    }

    public Lugar() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.descricao);
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + this.identificacao;
        hash = 71 * hash + Objects.hashCode(this.rua);
        hash = 71 * hash + Objects.hashCode(this.cidade);
        hash = 71 * hash + Objects.hashCode(this.estado);
        hash = 71 * hash + this.numero;
        hash = 71 * hash + Objects.hashCode(this.emailUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lugar other = (Lugar) obj;
        if (this.identificacao != other.identificacao) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.emailUsuario, other.emailUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lugar{" + "descricao=" + descricao + ", nome=" + nome + ", identificacao=" + identificacao + ", rua=" + rua + ", cidade=" + cidade + ", estado=" + estado + ", numero=" + numero + ", emailUsuario=" + emailUsuario + '}';
    }

}
