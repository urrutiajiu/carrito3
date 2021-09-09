package com.jurrutia.carrito.dao;

import java.util.List;

import com.jurrutia.carrito.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDao extends JpaRepository<Producto,Long>{
  @Query(value = "select DISTINCT p.*	from CLIENTE c "
    + "	INNER join CARRITO c2 on c2.ID_CLIENTE = c.ID_CLIENTE "
  	+  "INNER join CARRITO_ITEM ci on c2.ID_CARRITO = ci.ID_CARRITO"
	  + " INNER join PRODUCTO p on p.ID_PRODUCTO =ci.ID_PRODUCTO "
	  + " WHERE c.DNI = ?1 ORDER BY p.PRECIO_UNITARIO DESC", nativeQuery = true )
  public List<Producto> getAllProductosByDni(Long dni);
	 
}
