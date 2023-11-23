package com.tevology.logistica.models.services.maestros;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tevology.logistica.models.entity.maestros.Cliente;

public interface IClienteService {
	
	public Cliente findById(Integer id);
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAllPageAndSort(String columnSort, int order, int page);

	public Cliente save(Cliente cliente);
}