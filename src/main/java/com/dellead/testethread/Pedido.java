package com.dellead.testethread;

import java.io.Serializable;

public class Pedido implements Serializable {

    private int codigo;
    private double valor;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
