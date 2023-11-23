package com.tevology.logistica.controllers.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tevology.logistica.models.entity.seguridad.Modulo;
import com.tevology.logistica.models.services.seguridad.IModuloService;

@RestController
@RequestMapping("/api")
public class ModuloRestController {

	@Autowired
	private IModuloService moduloService;
	
	@GetMapping("/modulo/all")
	public List<Modulo> getAllModulo() {
		return moduloService.findAll();
	}
	
	@GetMapping("/modulo/modulosByUsername/{username}")
	public List<Modulo> getModulosByUsuarioId(@PathVariable String username){
		return moduloService.findModulosByUsername(username);
	}
}
