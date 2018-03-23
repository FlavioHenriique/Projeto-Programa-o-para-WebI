package com.ifpb.sisride.modelo;

import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Usuario implements Serializable {

    private String email;
    private String senha;
    private String nome;
    private LocalDate nascimento;
    private String profissao;
    private float nota_motorista;
    private float nota_passageiro;
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

    public Usuario(String email, String senha, String nome, LocalDate nascimento,
            String profissao, String cidade, String sexo) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.nascimento = nascimento;
        this.profissao = profissao;
        this.cidade = cidade;
        this.sexo = sexo;
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

    public float getNota_motorista() {
        return nota_motorista;
    }

    public void setNota_motorista(float nota_motorista) {
        this.nota_motorista = nota_motorista;
    }

    public float getNota_passageiro() {
        return nota_passageiro;
    }

    public void setNota_passageiro(float nota_passageiro) {
        this.nota_passageiro = nota_passageiro;
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

}
