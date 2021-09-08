package com.jurrutia.carrito.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.web.servlet.FlashMapManager;

import lombok.Data;

@Entity
@Table(name="CARRITO_ITEM")
public @Data class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CARRITO_DETALLE")
    private Long idCarritoDetalle;
    
    @JsonIgnore 
    @ManyToOne
    @JoinColumn(name = "ID_CARRITO",nullable = false)
    private Carrito carrito;
    
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO",nullable = false)
    private Producto producto;

    @Column(name="CANTIDAD")
    private int cantidad;

    @Column(name="PRECIO_UNITARIO")
    private BigDecimal precioUnitario;

    
}
