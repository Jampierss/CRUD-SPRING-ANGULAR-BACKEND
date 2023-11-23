package com.tevology.logistica.models.services.seguridad;

import java.util.List;

import com.tevology.logistica.models.entity.seguridad.Modulo;

public interface IModuloService {

	public List<Modulo> findAll();
	
	public List<Modulo> findModulosByUsername(String username);
	
}
