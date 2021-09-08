package com.jurrutia.carrito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CLIENTE")
public @Data class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CLIENTE",unique = true)
    private Long idCliente;
    
    @Column(name="DNI",nullable = false)
    private Long dni;

    @Column(name="NOMBRE",nullable = false,length = 50)
    private String nombre;

    @Column(name="ES_VIP",nullable = false)
    private boolean esVip;


}
