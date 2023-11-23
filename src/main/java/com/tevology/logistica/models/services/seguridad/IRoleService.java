package com.tevology.logistica.models.services.seguridad;

import java.util.List;

import com.tevology.logistica.models.entity.seguridad.Role;

public interface IRoleService {

	public List<Role> findAll();
	
	public Role findById(Integer id);
}
