package com.ifpb.sisride.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Avaliacao {

    private String comentario;
    private float nota;
    private Usuario usuarioAvaliado;
    private Usuario avaliador;
    private int codigo;
    private String tipo;

    public Avaliacao(String comentario, float nota, Usuario usuarioAvaliado,
            Usuario avaliador, String tipo) {
        this.comentario = comentario;
        this.nota = nota;
        this.usuarioAvaliado = usuarioAvaliado;
        this.avaliador = avaliador;
        this.tipo = tipo;
    }
    
    public Avaliacao(){
    }
    
    public Avaliacao(String comentario, float nota,
            Usuario usuarioAvaliado, Usuario avaliador, int codigo) {
        this.comentario = comentario;
        this.nota = nota;
        this.usuarioAvaliado = usuarioAvaliado;
        this.avaliador = avaliador;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Usuario getUsuarioAvaliado() {
        return usuarioAvaliado;
    }

    public void setUsuarioAvaliado(Usuario usuarioAvaliado) {
        this.usuarioAvaliado = usuarioAvaliado;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.comentario);
        hash = 13 * hash + Float.floatToIntBits(this.nota);
        hash = 13 * hash + Objects.hashCode(this.usuarioAvaliado);
        hash = 13 * hash + Objects.hashCode(this.avaliador);
        hash = 13 * hash + this.codigo;
        hash = 13 * hash + Objects.hashCode(this.tipo);
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
        final Avaliacao other = (Avaliacao) obj;
        if (Float.floatToIntBits(this.nota) != Float.floatToIntBits(other.nota)) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.comentario, other.comentario)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.usuarioAvaliado, other.usuarioAvaliado)) {
            return false;
        }
        if (!Objects.equals(this.avaliador, other.avaliador)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "comentario=" + comentario + ", nota=" + nota + ", usuarioAvaliado=" + usuarioAvaliado + ", avaliador=" + avaliador + ", codigo=" + codigo + ", tipo=" + tipo + '}';
    }

}
