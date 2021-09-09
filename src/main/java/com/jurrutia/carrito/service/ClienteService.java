package com.jurrutia.carrito.service;

import com.jurrutia.carrito.model.Cliente;

public interface ClienteService {

  Cliente getByDni(Long dni);
  
}
