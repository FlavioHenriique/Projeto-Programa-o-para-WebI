package com.ifpb.sisride.modelo;

public class Notificacao {

    private String notificador;
    private String notificado;
    private String mensagem;
    private String situacao;
    private String tipo;

    public Notificacao(String notificador, String notificado, String mensagem, String situacao, String tipo) {
        this.notificador = notificador;
        this.notificado = notificado;
        this.mensagem = mensagem;
        this.situacao = situacao;
        this.tipo = tipo;
    }

    public Notificacao() {
    }

    public String getNotificador() {
        return notificador;
    }

    public void setNotificador(String notificador) {
        this.notificador = notificador;
    }

    public String getNotificado() {
        return notificado;
    }

    public void setNotificado(String notificado) {
        this.notificado = notificado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
