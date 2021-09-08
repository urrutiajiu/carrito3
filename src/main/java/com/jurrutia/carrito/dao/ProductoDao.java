package com.jurrutia.carrito.dao;

import com.jurrutia.carrito.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDao extends JpaRepository<Producto,Long>{
  
}
