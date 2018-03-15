package com.ifpb.sisride.modelo;

import java.util.Objects;

public class Solicitacao {

    private Usuario usuario;
    private Usuario amigo;
    private String tipo;
    private String situacao;

    public Solicitacao(Usuario usuario, Usuario amigo, String tipo, String situacao) {
        this.usuario = usuario;
        this.amigo = amigo;
        this.tipo = tipo;
        this.situacao = situacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getAmigo() {
        return amigo;
    }

    public void setAmigo(Usuario amigo) {
        this.amigo = amigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.usuario);
        hash = 67 * hash + Objects.hashCode(this.amigo);
        hash = 67 * hash + Objects.hashCode(this.tipo);
        hash = 67 * hash + Objects.hashCode(this.situacao);
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
        final Solicitacao other = (Solicitacao) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.situacao, other.situacao)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.amigo, other.amigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Solicitacao{" + "usuario=" + usuario + ", amigo=" + amigo + ", "
                + "tipo=" + tipo + ", situacao=" + situacao + '}';
    }

}
