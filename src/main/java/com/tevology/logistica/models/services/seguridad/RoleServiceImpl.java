package com.tevology.logistica.models.services.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tevology.logistica.models.dao.seguridad.IRoleDao;
import com.tevology.logistica.models.entity.seguridad.Role;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	
	@Override
	public Role findById(Integer id) {
		return roleDao.findById(id).orElse(null);
	}

}
