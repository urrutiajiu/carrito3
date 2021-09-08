package com.jurrutia.carrito.model;

public enum TipoCarrito {

  COMUN("COM","COMUN"),
  PROMOCIONAL("PROMO","PROMOCIONAL"),
  VIP("VIP","VIP");

  private final String codigo;
  private final String valor;

  TipoCarrito(String codigo, String valor) {
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
