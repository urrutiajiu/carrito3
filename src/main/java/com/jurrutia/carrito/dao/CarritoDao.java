package com.jurrutia.carrito.dao;

import com.jurrutia.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoDao extends JpaRepository<Carrito,Long> {
  
}
