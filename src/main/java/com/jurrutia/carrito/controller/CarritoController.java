package com.jurrutia.carrito.controller;

import com.jurrutia.carrito.dto.request.CarritoRequest;
import com.jurrutia.carrito.dto.response.CarritoResponse;
import com.jurrutia.carrito.model.Carrito;
import com.jurrutia.carrito.model.Cliente;
import com.jurrutia.carrito.service.CarritoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrito/v1")
public class CarritoController {

  @Autowired
  private CarritoService carritoService;

  @Autowired
  private ModelMapper modelMapper;


  @GetMapping("/consultar/{id}")
  public CarritoResponse getCarrito(@PathVariable("id") Long idCarrito){
    return modelMapper.map(carritoService.getCarritoById(idCarrito), CarritoResponse.class);
  }

  @PostMapping("/create")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Long crearCarrito(@RequestBody CarritoRequest carritoRequest) {

    Cliente cliente = modelMapper.map(carritoRequest, Cliente.class);

    return carritoService.add(cliente).getIdCarrito();
  }

  @DeleteMapping("/delete/{idCarrito}")
  @ResponseStatus(code=HttpStatus.OK)
  public void borrarCarrito(@PathVariable("idCarrito") Long idCarrito){
      carritoService.delete(idCarrito);
  }

  @PostMapping("/agregarProducto/{idCarrito}/{idProducto}")
  public CarritoResponse agregarProducto(@PathVariable("idCarrito") Long idCarrito,@PathVariable("idProducto") Long idProducto)
  {
    Carrito carrito = carritoService.addProductoToCarrito(idCarrito,idProducto);
    //List<CarritoDetalleDto> carritoDetalleDto = modelMapper.mapList(carrito.getDetalleCarrito(),CarritoDetalleDto.class);
    return modelMapper.map(carrito, CarritoResponse.class);
  }

  @DeleteMapping("/eliminarProducto/{idCarrito}/{idProducto}")
  public CarritoResponse eliminarProducto(@PathVariable("idCarrito") Long idCarrito,@PathVariable("idProducto") Long idProducto){
    Carrito carrito = carritoService.deleteProductoOfCarrito(idCarrito,idProducto);
    return modelMapper.map(carrito, CarritoResponse.class);
  }

  // @GetMapping("/obtenerTop4/{dni}")
  // public ProductoDto obtenerTop4(@PathVariable("dni") Long dni) {

  //   return modelMapper.map(producto, CarritoDto.class);
  // }
}
