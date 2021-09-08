package com.jurrutia.carrito.service;

import com.jurrutia.carrito.model.Carrito;
import com.jurrutia.carrito.model.Cliente;

public interface CarritoService {

  Carrito add(Cliente cliente);
  void delete(Long idCarrito);
  Carrito addProductoToCarrito(Long idCarrito, Long idProducto);
  Carrito deleteProductoOfCarrito(Long idCarrito, Long idProducto);
  
  
  
}
