package com.ifpb.sisride.modelo;

import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Usuario implements Serializable{

    private String email;
    private String senha;
    private String nome;
    private LocalDate nascimento;
    private String profissao;
    private float nota;
    private String cidade;
    private String sexo;
    private InputStream foto;
    private byte[] foto2;

    public Usuario(String email, String senha, String nome, LocalDate nascimento,
            String profissao, String cidade, String sexo, InputStream foto) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.nascimento = nascimento;
        this.profissao = profissao;
        this.cidade = cidade;
        this.sexo = sexo;
        this.foto = foto;

    }

    public Usuario(String email, String senha, String nome, LocalDate nascimento,
            String profissao, String cidade, String sexo, byte[] foto2) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.nascimento = nascimento;
        this.profissao = profissao;
        this.cidade = cidade;
        this.sexo = sexo;
        this.foto2 = foto2;
    }

    public byte[] getFoto2() {
        return foto2;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public Usuario() {

    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.senha);
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + Objects.hashCode(this.nascimento);
        hash = 83 * hash + Objects.hashCode(this.profissao);
        hash = 83 * hash + Float.floatToIntBits(this.nota);
        hash = 83 * hash + Objects.hashCode(this.cidade);
        hash = 83 * hash + Objects.hashCode(this.sexo);
        hash = 83 * hash + Objects.hashCode(this.foto);
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
        final Usuario other = (Usuario) obj;
        if (Float.floatToIntBits(this.nota) != Float.floatToIntBits(other.nota)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.profissao, other.profissao)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.nascimento, other.nascimento)) {
            return false;
        }
        if (!Objects.equals(this.foto, other.foto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", senha=" + senha + ", nome=" + nome + ", nascimento=" + nascimento + ", profissao=" + profissao + ", nota=" + nota + ", cidade=" + cidade + ", sexo=" + sexo + ", foto=" + foto + '}';
    }

}
