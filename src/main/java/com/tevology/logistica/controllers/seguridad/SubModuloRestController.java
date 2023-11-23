package com.tevology.logistica.controllers.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tevology.logistica.models.entity.seguridad.SubModulo;
import com.tevology.logistica.models.services.seguridad.ISubModuloService;

@RestController
@RequestMapping("/api")
public class SubModuloRestController {
	
	@Autowired
	private ISubModuloService subModuloService;
	
	@GetMapping("/subModulo/all")
	public List<SubModulo> getAllSubModulo() {
		return subModuloService.findAll();
	}
	
	@GetMapping("/subModuloById/{id}")
	public List<SubModulo> getAllSubModulo(@PathVariable Integer id) {
		return subModuloService.findAllByIdModulo(id);
	}

}
