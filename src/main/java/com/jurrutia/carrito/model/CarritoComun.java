package com.jurrutia.carrito.model;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Entity
public class CarritoComun extends Carrito {

   
    public CarritoComun() {
        super();
        this.setTipoCarrito(TipoCarrito.COMUN);
    }

    
    @Override
    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);
       
        for (CarritoItem item : this.getItems()) {
            total = total.add(new BigDecimal(item.getCantidad()).multiply(item.getPrecioUnitario(), MathContext.DECIMAL32));
        }
        return total;
    }
    
}
