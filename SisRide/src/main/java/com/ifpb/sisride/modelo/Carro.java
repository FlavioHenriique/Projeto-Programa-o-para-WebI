package com.ifpb.sisride.modelo;

import java.util.Objects;

public class Carro {

    private String modelo;
    private String ano;
    private boolean ar_condicionado;
    private int codigo;

    public Carro() {

    }

    public Carro(String modelo, String ano, boolean ar_condicionado) {
        this.modelo = modelo;
        this.ano = ano;
        this.ar_condicionado = ar_condicionado;
    }

    public Carro(String modelo, String ano, boolean ar_condicionado, int codigo) {
        this.modelo = modelo;
        this.ano = ano;
        this.ar_condicionado = ar_condicionado;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public boolean isAr_condicionado() {
        return ar_condicionado;
    }

    public void setAr_condicionado(boolean ar_condicionado) {
        this.ar_condicionado = ar_condicionado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.modelo);
        hash = 13 * hash + Objects.hashCode(this.ano);
        hash = 13 * hash + (this.ar_condicionado ? 1 : 0);
        hash = 13 * hash + this.codigo;
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
        final Carro other = (Carro) obj;
        if (this.ar_condicionado != other.ar_condicionado) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.ano, other.ano)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carro{" + "modelo=" + modelo + ", ano=" + ano + ", ar_condicionado=" + ar_condicionado + ", codigo=" + codigo + '}';
    }

}
