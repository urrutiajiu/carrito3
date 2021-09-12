package com.jurrutia.carrito.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;

import javax.persistence.Entity;

@Entity
public class CarritoVip extends Carrito {

    private static final BigDecimal DESCUENTO = new BigDecimal(700);

    public CarritoVip() {
        super();
        this.setTipoCarrito(TipoCarrito.VIP);
    }

    @Override
    public BigDecimal getTotal() {
        if (this.getItems().size() == 0)
            return new BigDecimal(0);

        MathContext mContext = new MathContext(2);
        BigDecimal total = new BigDecimal(0,mContext);
        BigDecimal productoMasBarato = this.getItems().stream().min(Comparator.comparing(CarritoItem::getPrecioUnitario)).get().getPrecioUnitario();
       
        
        for (CarritoItem item : this.getItems()) {
            total = total.add(new BigDecimal(item.getCantidad()).multiply(item.getPrecioUnitario(), MathContext.DECIMAL32));
        }

        
        if (this.getItems().size() == 10)
            total = total.subtract(total.multiply(new BigDecimal(0.1)));
        else if ((this.getItems().size() > 5) && (total.compareTo(productoMasBarato.add(DESCUENTO))>= 0)) {
            total = total.subtract(productoMasBarato.add(DESCUENTO));
        }
        return total;
    }
    
}
