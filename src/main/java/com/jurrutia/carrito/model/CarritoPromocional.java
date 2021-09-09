package com.jurrutia.carrito.model;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.persistence.Entity;

@Entity
public class CarritoPromocional extends Carrito {


    private static final BigDecimal DESCUENTO = new BigDecimal(500);

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
        if (this.getItems().size() == 10)
            total = total.subtract(total.multiply(new BigDecimal(0.1)));
        else if ((this.getItems().size() > 5)&&(total.compareTo(DESCUENTO)>= 0)) {
            total = total.subtract(DESCUENTO);
        }
                   
        
        return total;
    }

    
}
