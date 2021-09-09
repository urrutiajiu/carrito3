package com.jurrutia.carrito.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.jurrutia.carrito.model.CarritoItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarritoResponse {
  
  private Long idCarrito;
  private BigDecimal total;
  private String tipo;
  private String estado;
  private List<CarritoItem> items;

}
