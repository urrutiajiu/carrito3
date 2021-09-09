package com.jurrutia.carrito.dao;

import java.util.List;

import com.jurrutia.carrito.model.Carrito;
import com.jurrutia.carrito.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoDao extends JpaRepository<Carrito,Long> {

  public List<Carrito> getCarritosByCliente(Cliente cliente);
  
}
