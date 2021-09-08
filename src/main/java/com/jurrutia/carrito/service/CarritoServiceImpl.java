package com.jurrutia.carrito.service;

import java.util.Date;
import java.util.HashSet;

import com.jurrutia.carrito.dao.CarritoDao;
import com.jurrutia.carrito.dao.ClienteDao;
import com.jurrutia.carrito.dao.ProductoDao;
import com.jurrutia.carrito.model.Carrito;
import com.jurrutia.carrito.model.CarritoComun;
import com.jurrutia.carrito.model.CarritoItem;
import com.jurrutia.carrito.model.Cliente;
import com.jurrutia.carrito.model.Estado;
import com.jurrutia.carrito.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoServiceImpl implements CarritoService {

  @Autowired
  ClienteDao clienteDao;
  @Autowired
  private CarritoDao carritoDao;
  @Autowired
  private ProductoDao productoDao;


  @Override
  public Carrito getCarritoById(Long idCarrito){
    return carritoDao.findById(idCarrito).orElse(null);
  }


  @Override
  public Carrito add(Cliente cliente) {
    Carrito carrito = new CarritoComun();
    carrito.setEstado(Estado.EN_CURSO);
    carrito.setCliente(clienteDao.findByDni(cliente.getDni()));
    carrito.setItems(new HashSet<CarritoItem>());
    carrito.setFecha(new Date());
    //carrito.setTotal(new BigDecimal(0));
    return carritoDao.save(carrito);
  }

  @Override
  public void delete(Long idCarrito) {
    Carrito carrito = carritoDao.getById(idCarrito);
    carritoDao.delete(carrito);    
  }

  @Override
  public Carrito addProductoToCarrito(Long idCarrito, Long idProducto) {
    Carrito carrito = carritoDao.findById(idCarrito).orElse(null);
    Producto producto = productoDao.findById(idProducto).orElse(null);
    CarritoItem carritoDetalle = new CarritoItem();
    carritoDetalle.setCantidad(1);
    carritoDetalle.setCarrito(carrito);
    carritoDetalle.setProducto(producto);
    carritoDetalle.setPrecioUnitario(producto.getPrecioUnitario());
    //carrito.agregarItem(carritoDetalle);
    carrito.getItems().add(carritoDetalle);
    carritoDao.save(carrito);
    return carrito;
  }

  @Override
  public Carrito deleteProductoOfCarrito(Long idCarrito, Long idProducto) {
    Carrito carrito = carritoDao.findById(idCarrito).orElse(null);
    CarritoItem itemToDelete = null;
    for (CarritoItem item : carrito.getItems()) {
      if (item.getProducto().getIdProducto().equals(idProducto))
          itemToDelete = item;
    }
    carrito.getItems().remove(itemToDelete);
    return carritoDao.save(carrito);
  }
  
}
