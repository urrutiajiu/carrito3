package com.jurrutia.carrito.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.jurrutia.carrito.dto.response.CarritoResponse;
import com.jurrutia.carrito.dto.response.ClienteResponse;
import com.jurrutia.carrito.dto.response.ProductoResponse;
import com.jurrutia.carrito.model.Carrito;
import com.jurrutia.carrito.model.Cliente;
import com.jurrutia.carrito.model.Producto;
import com.jurrutia.carrito.service.CarritoService;
import com.jurrutia.carrito.service.ClienteService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carrito/v1")
public class CarritoController {

  @Autowired
  private CarritoService carritoService;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ModelMapper modelMapper;


  /**
   * Devuelve el listado de carritos
   * @return List<CarritoResponse>
   */
  @GetMapping("/listar")
  public List<CarritoResponse> listarCarrito(){
    List<Carrito> carritos = carritoService.listaCarritos();
    List<CarritoResponse> response = carritos
    .stream()
    .map(carrito -> modelMapper.map(carrito, CarritoResponse.class)).collect(Collectors.toList());

    return response;
    
  }
  
  /**
   * Devuelve un carrito a partir de su Id 
   * @param idCarrito
   * @return CarritoResponse
   */
  @GetMapping("/consultar/{id}")
  public CarritoResponse getCarrito(@PathVariable("id") Long idCarrito){
    Carrito carrito = carritoService.getCarritoById(idCarrito);
    if (carrito != null)
      return modelMapper.map(carrito, CarritoResponse.class);
    else
      return null;
  }

  /**
   * Crea un carrito para un cliente(dni) y devuelve su id
   * @return Long
   * 
  */
  @PostMapping("/create/{dni}")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Long crearCarrito(@PathVariable ("dni") Long dni) {

    Cliente cliente = clienteService.getByDni(dni);
    
    return carritoService.add(cliente).getIdCarrito();
  }

  /**
   * Elimina un carrito por su id
   * @return Long
   * 
  */
  @DeleteMapping("/delete/{idCarrito}")
  @ResponseStatus(code=HttpStatus.OK)
  public void borrarCarrito(@PathVariable("idCarrito") Long idCarrito){
      carritoService.delete(idCarrito);
  }

  /**
   * Agregar producto a un carrito existente
   * @param idCarrito
   * @param idProducto
   * @return CarritoResponse
   */
  @PostMapping("/agregarProducto/{idCarrito}/{idProducto}")
  
  public CarritoResponse agregarProducto(@PathVariable("idCarrito") Long idCarrito,@PathVariable("idProducto") Long idProducto)
  {
    Carrito carrito = carritoService.addProductoToCarrito(idCarrito,idProducto);
    return modelMapper.map(carrito, CarritoResponse.class);
  }

  /**
   * Elimina un producto de un carrito
   * @param idCarrito
   * @param idProducto
   * @return CarritoResponse
   */
  @PostMapping("/eliminarProducto/{idCarrito}/{idProducto}")
  public CarritoResponse eliminarProducto(@PathVariable("idCarrito") Long idCarrito,@PathVariable("idProducto") Long idProducto){
    Carrito carrito = carritoService.deleteProductoOfCarrito(idCarrito,idProducto);
    return modelMapper.map(carrito, CarritoResponse.class);
  }

   /**
   * Obtiene un lista con los 4 productos más caros para un Cliente(dni) en particular
   * @param dni
   * @return List<ProductoResponse>
   */
  @GetMapping("/obtenerTop4/{dni}")
  public List<ProductoResponse> obtenerTop4(@PathVariable("dni") Long dni) {

    List<Producto> productos = carritoService.obtenerTop4(dni);
    List<ProductoResponse> response = productos
      .stream()
      .map(producto -> modelMapper.map(producto, ProductoResponse.class)).collect(Collectors.toList());
    return response;
  }

  /**
   * Obtiene una lista de los productos
   * @return List<ProductoResponse>
  */
  @GetMapping("/listarProductos")
  public List<ProductoResponse> getProductos() {
    List<Producto> productos = carritoService.getProductos();
    List<ProductoResponse> response = productos.stream()
                                      .map(producto -> modelMapper.map(producto, ProductoResponse.class)).collect(Collectors.toList());
    return response;
  }


  /**
   * Obtiene una lista de los clientes
   * @return List<ClienteResponse>
  */
  @GetMapping("/listarClientes")
  public List<ClienteResponse> getClientes() {
    List<Cliente> clientes = carritoService.getClientes();
    List<ClienteResponse> response = clientes.stream()
                                      .map(cliente -> modelMapper.map(cliente, ClienteResponse.class)).collect(Collectors.toList());
    return response;
  }
}