package com.tevology.logistica.models.services.maestros;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tevology.logistica.models.entity.maestros.Cargo;

public interface ICargoService {

	public Cargo findById(Integer id);
	
	public List<Cargo> findAll();
	
	public Page<Cargo> findAllPageAndSort(String nombre, Integer estadoId, String columnSort, int order, int page);
	
	public List<Cargo> autocompleteList(String term);
	
	public Cargo save(Cargo cargo);
}
