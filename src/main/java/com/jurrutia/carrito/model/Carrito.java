package com.jurrutia.carrito.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CARRITO")
public abstract @Data class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARRITO", nullable=false)
    private Long idCarrito;
    
    @Column(name = "FECHA")
    private Date fecha;

    
    @Enumerated(EnumType.STRING)
    @Column(name="ESTADO",nullable = false)
    private Estado estado; 

    @Enumerated(EnumType.STRING)
    @Column(name="TIPO",nullable = false) 
    private TipoCarrito tipoCarrito;

    @Transient
    //@Column(name="TOTAL")
    public abstract BigDecimal getTotal();

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @OneToMany(mappedBy = "carrito",cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    @EqualsAndHashCode.Exclude 
    private Set<CarritoItem> items;


    public void agregarItem(CarritoItem item) {
        item.setCarrito(this);
        this.items.add(item);
      }
    
      public void eliminarItem(CarritoItem item) {
          if (item != null) {
            item.setCarrito(null);
            this.items.remove(item);
          }
        
      }

}
