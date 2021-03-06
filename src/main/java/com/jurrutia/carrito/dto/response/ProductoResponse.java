package com.jurrutia.carrito.dto.response;


import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponse {
  
  private Long idProducto;
  private String nombre;
  private BigDecimal precioUnitario;

}
