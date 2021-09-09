package com.jurrutia.carrito.service;

import com.jurrutia.carrito.dao.ClienteDao;
import com.jurrutia.carrito.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
  
  @Autowired
  private ClienteDao clienteDao;

	@Override
	public Cliente getByDni(Long dni) {
		return clienteDao.findByDni(dni);
	}

  
  
}
