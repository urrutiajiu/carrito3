package com.jurrutia.carrito.model;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Entity
public class CarritoPromocional extends Carrito {


    public CarritoPromocional() {
        super();
        this.setTipoCarrito(TipoCarrito.PROMOCIONAL);
    }

    @Override
    public BigDecimal getTotal() {
        
        BigDecimal total = new BigDecimal(0);
       
        for (CarritoItem item : this.getItems()) {
            total = total.add(new BigDecimal(item.getCantidad()).multiply(item.getPrecioUnitario(), MathContext.DECIMAL32));
        }

        if (this.getItems().size() > 5)
            return new BigDecimal(3);
        
        return new BigDecimal(5);
    }

    
}
