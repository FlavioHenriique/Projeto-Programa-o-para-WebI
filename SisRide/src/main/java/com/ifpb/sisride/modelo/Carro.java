package com.ifpb.sisride.modelo;

import java.util.Objects;

public class Carro {

    private String modelo;
    private int ano;
    private boolean ar_condicionado;
    private static int quant;
    private int codigo;

    public Carro(String modelo, int ano, boolean ar_condicionado) {
        this.modelo = modelo;
        this.ano = ano;
        this.ar_condicionado = ar_condicionado;
        this.codigo = ++quant;
    }

    public int getCodigo() {
        return codigo;
    }
    
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
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
        hash = 29 * hash + Objects.hashCode(this.modelo);
        hash = 29 * hash + this.ano;
        hash = 29 * hash + (this.ar_condicionado ? 1 : 0);
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
        if (this.ano != other.ano) {
            return false;
        }
        if (this.ar_condicionado != other.ar_condicionado) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carro{" + "modelo=" + modelo + ", ano=" + ano + ", ar_condicionado=" + ar_condicionado + '}';
    }

}
