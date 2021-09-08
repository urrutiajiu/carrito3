package com.jurrutia.carrito.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="PRODUCTO")
public @Data class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PRODUCTO")
    private Long idProducto;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name="PRECIO_UNITARIO",scale = 2,precision = 10)
    private BigDecimal precioUnitario;
    

    
    
}
