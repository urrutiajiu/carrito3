package com.jurrutia.carrito.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class CarritoVip extends Carrito {

    public CarritoVip() {
        super();
        this.setTipoCarrito(TipoCarrito.VIP);
    }

    @Override
    public BigDecimal getTotal() {
        if (this.getItems().size() == 10)
            return new BigDecimal(10);

        return new BigDecimal(5);
    }
    
}
