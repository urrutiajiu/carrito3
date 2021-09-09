package com.jurrutia.carrito.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.jurrutia.carrito.dao.CarritoDao;
import com.jurrutia.carrito.dao.ClienteDao;
import com.jurrutia.carrito.dao.ProductoDao;
import com.jurrutia.carrito.model.Carrito;
import com.jurrutia.carrito.model.CarritoComun;
import com.jurrutia.carrito.model.CarritoItem;
import com.jurrutia.carrito.model.CarritoPromocional;
import com.jurrutia.carrito.model.CarritoVip;
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
    Carrito carrito;
    if (cliente.isEsVip())
      carrito = new CarritoVip();
    else if (this.fechaPromocional())
      carrito = new CarritoPromocional();
    else
      carrito = new CarritoComun();

    carrito.setEstado(Estado.EN_CURSO);
    carrito.setCliente(cliente);
    carrito.setItems(new HashSet<CarritoItem>());
    carrito.setFecha(new Date());
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

  private boolean fechaPromocional(){
    Random random = new Random();
    int valor = random.nextInt(1000);
    return (valor%2 == 0);
  }


	@Override
	public List<Producto> obtenerTop4(Long dni) {
    Cliente cliente = clienteDao.findByDni(dni);
		List<Carrito> carritos = carritoDao.getCarritosByCliente(cliente);
    carritos.stream().map(Carrito::getItems).sorted();
		return new ArrayList<Producto>();

	}

}
