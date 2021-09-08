package com.jurrutia.carrito.model;

public enum Estado {

  EN_CURSO ("EN_CURSO","En curso"),
  CANCELADO ("CANCELADO","Cancelado"),
  FINALIZADO("FINALIZADO","Finalizado");


  private final String codigo;
  private final String valor;

  Estado(String codigo, String valor) {
    this.codigo = codigo;
    this.valor = valor;
  }

  public String getCodigo() {
    return this.codigo;
  }

  public String getValor() {
    return this.valor;
  }
}