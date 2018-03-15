package com.ifpb.sisride.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Viagem {

    private int vagas;
    private LocalDate data;
    private String hora;
    private float valor;
    private Usuario motorista;
    private String musica;
    private boolean animais;
    private boolean fumar;
    private String conversa;
    private Lugar destino;
    private Lugar partida;
    private int codCarro;
    private int codigo;

    public Viagem(int vagas, LocalDate data, String hora, float valor,
            Usuario motorista, String musica, boolean animais, boolean fumar,
            String conversa, Lugar destino, Lugar partida, int codCarro) {
        this.vagas = vagas;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.motorista = motorista;
        this.musica = musica;
        this.animais = animais;
        this.fumar = fumar;
        this.conversa = conversa;
        this.destino = destino;
        this.partida = partida;
        this.codCarro = codCarro;
    }

    public Viagem(int vagas, LocalDate data, String hora, float valor,
            Usuario motorista, String musica, boolean animais, boolean fumar,
            String conversa, Lugar destino, Lugar partida, int codCarro, int codigo) {
        this.vagas = vagas;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.motorista = motorista;
        this.musica = musica;
        this.animais = animais;
        this.fumar = fumar;
        this.conversa = conversa;
        this.destino = destino;
        this.partida = partida;
        this.codCarro = codCarro;
        this.codigo = codigo;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Usuario getMotorista() {
        return motorista;
    }

    public void setMotorista(Usuario motorista) {
        this.motorista = motorista;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public boolean isAnimais() {
        return animais;
    }

    public void setAnimais(boolean animais) {
        this.animais = animais;
    }

    public boolean isFumar() {
        return fumar;
    }

    public void setFumar(boolean fumar) {
        this.fumar = fumar;
    }

    public String getConversa() {
        return conversa;
    }

    public void setConversa(String conversa) {
        this.conversa = conversa;
    }

    public Lugar getDestino() {
        return destino;
    }

    public void setDestino(Lugar destino) {
        this.destino = destino;
    }

    public Lugar getPartida() {
        return partida;
    }

    public void setPartida(Lugar partida) {
        this.partida = partida;
    }

    public int getCodCarro() {
        return codCarro;
    }

    public void setCodCarro(int codCarro) {
        this.codCarro = codCarro;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.vagas;
        hash = 67 * hash + Objects.hashCode(this.data);
        hash = 67 * hash + Objects.hashCode(this.hora);
        hash = 67 * hash + Float.floatToIntBits(this.valor);
        hash = 67 * hash + Objects.hashCode(this.motorista);
        hash = 67 * hash + Objects.hashCode(this.musica);
        hash = 67 * hash + (this.animais ? 1 : 0);
        hash = 67 * hash + (this.fumar ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.conversa);
        hash = 67 * hash + Objects.hashCode(this.destino);
        hash = 67 * hash + Objects.hashCode(this.partida);
        hash = 67 * hash + this.codCarro;
        hash = 67 * hash + this.codigo;
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
        final Viagem other = (Viagem) obj;
        if (this.vagas != other.vagas) {
            return false;
        }
        if (Float.floatToIntBits(this.valor) != Float.floatToIntBits(other.valor)) {
            return false;
        }
        if (this.animais != other.animais) {
            return false;
        }
        if (this.fumar != other.fumar) {
            return false;
        }
        if (this.codCarro != other.codCarro) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.musica, other.musica)) {
            return false;
        }
        if (!Objects.equals(this.conversa, other.conversa)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.motorista, other.motorista)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        if (!Objects.equals(this.partida, other.partida)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Viagem{" + "vagas=" + vagas + ", data=" + data + ", hora=" + hora + ", valor=" + valor + ", motorista=" + motorista + ", musica=" + musica + ", animais=" + animais + ", fumar=" + fumar + ", conversa=" + conversa + ", destino=" + destino + ", partida=" + partida + ", codCarro=" + codCarro + ", codigo=" + codigo + '}';
    }

}
