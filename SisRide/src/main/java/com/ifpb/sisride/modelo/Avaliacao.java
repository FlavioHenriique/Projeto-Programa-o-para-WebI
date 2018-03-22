package com.ifpb.sisride.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Avaliacao {

    private String comentario;
    private float nota;
    private String usuarioAvaliado;
    private String avaliador;
    private int codigo;
    private String tipo;

    public Avaliacao(String comentario, float nota, String usuarioAvaliado,
            String avaliador, String tipo) {
        this.comentario = comentario;
        this.nota = nota;
        this.usuarioAvaliado = usuarioAvaliado;
        this.avaliador = avaliador;
        this.tipo = tipo;
    }

    public Avaliacao(String comentario, float nota,
            String usuarioAvaliado, String avaliador, int codigo) {
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

    public String getUsuarioAvaliado() {
        return usuarioAvaliado;
    }

    public void setUsuarioAvaliado(String usuarioAvaliado) {
        this.usuarioAvaliado = usuarioAvaliado;
    }

    public String getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(String avaliador) {
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
        hash = 53 * hash + Objects.hashCode(this.comentario);
        hash = 53 * hash + Float.floatToIntBits(this.nota);
        hash = 53 * hash + Objects.hashCode(this.usuarioAvaliado);
        hash = 53 * hash + Objects.hashCode(this.avaliador);
        hash = 53 * hash + this.codigo;
        hash = 53 * hash + Objects.hashCode(this.tipo);
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
        if (!Objects.equals(this.usuarioAvaliado, other.usuarioAvaliado)) {
            return false;
        }
        if (!Objects.equals(this.avaliador, other.avaliador)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "comentario=" + comentario + ", nota=" + nota + ", usuarioAvaliado=" + usuarioAvaliado + ", avaliador=" + avaliador + ", codigo=" + codigo + ", tipo=" + tipo + '}';
    }

}
