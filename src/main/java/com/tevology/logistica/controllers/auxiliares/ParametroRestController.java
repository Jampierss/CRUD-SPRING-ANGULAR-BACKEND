package com.tevology.logistica.controllers.auxiliares;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tevology.logistica.models.entity.auxiliares.Parametro;
import com.tevology.logistica.models.services.auxiliares.IParametroService;

@RestController
@RequestMapping("/api")
public class ParametroRestController {

	@Autowired
	private IParametroService parametroService;
	
	@GetMapping("/parametro/{id}")
	public Parametro getParametro(@PathVariable Integer id) {
		return parametroService.getParametroById(id);
	}
	
	@GetMapping("/parametro/parametros")
	public List<Parametro> getAllParametro() {
		return parametroService.getAllParametros();
	}
	
	@PutMapping("/parametro/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Parametro parametro, BindingResult result, @PathVariable Integer id){
		Parametro parametroActual = parametroService.getParametroById(id);
		
		
		Parametro parametroUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(parametroActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El parametro no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			parametroActual.setValor(parametro.getValor());
		
			parametroUpdate = parametroService.save(parametroActual);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Parametro actualizado exitosamente");
		response.put("parametro", parametroUpdate);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
