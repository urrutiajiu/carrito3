package com.jurrutia.carrito.service;

import java.util.List;

import com.jurrutia.carrito.model.Carrito;
import com.jurrutia.carrito.model.Cliente;
import com.jurrutia.carrito.model.Producto;

public interface CarritoService {

  Carrito getCarritoById(Long idCarrito);
  Carrito add(Cliente cliente);
  void delete(Long idCarrito);
  Carrito addProductoToCarrito(Long idCarrito, Long idProducto);
  Carrito deleteProductoOfCarrito(Long idCarrito, Long idProducto);
  List<Producto> obtenerTop4(Long dni);  
  
  
}
