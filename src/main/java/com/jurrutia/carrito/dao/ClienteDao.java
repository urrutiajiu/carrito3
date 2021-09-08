package com.jurrutia.carrito.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jurrutia.carrito.model.Cliente;

@Repository
public interface ClienteDao extends JpaRepository<Cliente,Long>{
  
  public Cliente findByDni(Long dni);
  
}
