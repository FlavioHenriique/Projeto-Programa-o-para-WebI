package com.ifpb.sisride.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Avaliacao {

    private String comentario;
    private String hora;
    private LocalDate data;
    private float nota;
    private String usuarioAvaliado;
    private String avaliador;
    private int codigo;

    public Avaliacao(String comentario, String hora, LocalDate data,
            float nota, String usuarioAvaliado, String avaliador) {
        this.comentario = comentario;
        this.hora = hora;
        this.data = data;
        this.nota = nota;
        this.usuarioAvaliado = usuarioAvaliado;
        this.avaliador = avaliador;
    }

    public Avaliacao(String comentario, String hora, LocalDate data, float nota,
            String usuarioAvaliado, String avaliador, int codigo) {
        this.comentario = comentario;
        this.hora = hora;
        this.data = data;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.comentario);
        hash = 37 * hash + Objects.hashCode(this.hora);
        hash = 37 * hash + Objects.hashCode(this.data);
        hash = 37 * hash + Float.floatToIntBits(this.nota);
        hash = 37 * hash + Objects.hashCode(this.usuarioAvaliado);
        hash = 37 * hash + Objects.hashCode(this.avaliador);
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
        if (!Objects.equals(this.comentario, other.comentario)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.usuarioAvaliado, other.usuarioAvaliado)) {
            return false;
        }
        if (!Objects.equals(this.avaliador, other.avaliador)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "comentario=" + comentario + ", hora=" + hora + ", data=" + data + ", nota=" + nota + ", usuarioAvaliado=" + usuarioAvaliado + ", avaliador=" + avaliador + ", codigo=" + codigo + '}';
    }

}
