package com.ifpb.sisride.modelo;

public class Mensagem {

    private String usuario;
    private String amigo;
    private String mensagem;

    public Mensagem(String usuario, String mensagem, String amigo) {
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.amigo = amigo;
    }

    public Mensagem() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getAmigo() {
        return amigo;
    }

    public void setAmigo(String amigo) {
        this.amigo = amigo;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "usuario=" + usuario + ", amigo=" + amigo + ", mensagem=" + mensagem + '}';
    }

}
