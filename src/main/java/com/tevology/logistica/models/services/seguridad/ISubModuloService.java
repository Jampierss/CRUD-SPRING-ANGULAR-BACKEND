package com.tevology.logistica.models.services.seguridad;

import java.util.List;

import com.tevology.logistica.models.entity.seguridad.SubModulo;

public interface ISubModuloService {

	public List<SubModulo> findAll();
	
	public List<SubModulo> findAllByIdModulo(Integer id);
}
