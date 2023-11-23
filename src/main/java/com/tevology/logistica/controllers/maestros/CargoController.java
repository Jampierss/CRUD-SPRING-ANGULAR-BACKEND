package com.tevology.logistica.controllers.maestros;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tevology.logistica.models.entity.maestros.Cargo;
import com.tevology.logistica.models.services.maestros.ICargoService;

@RestController
@RequestMapping("/api")
public class CargoController {
	
	@Autowired
	private ICargoService cargoService;
	
    @GetMapping("/cargo/busqueda/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        Cargo cargo = null;
        Map<String, Object> response = new HashMap<>();

        try {
            cargo  = cargoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la BD");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cargo == null) {
            response.put("mensaje", "El cargo no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cargo>(cargo, HttpStatus.OK);
    }
    
    @GetMapping("/cargo/listadoSelect")
    public List<Cargo> all() {
        return cargoService.findAll();
    }
    
    @PostMapping("/cargo")
    public ResponseEntity<?> create(@Valid @RequestBody Cargo cargo, BindingResult result) {
        Cargo cargoNew = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
        	cargo.setFechaCrea(new Date());
        	cargoNew = cargoService.save(cargo);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la BD");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cargo registrado exitosamente");
        response.put("cargo", cargoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/cargo/update/{id}")
    public ResponseEntity<?> updateCargo(@Valid @RequestBody Cargo cargo, BindingResult result, @PathVariable Integer id){

        Cargo cargoActual = cargoService.findById(id);
        Cargo cargoUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(cargoActual == null) {
            response.put("mensaje", "Error, no se pudo editar: El Cargo no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
        	
        	cargoActual.setNombre(cargo.getNombre());
        	cargoActual.setAbreviatura(cargo.getAbreviatura());
        	cargoActual.setObservacion(cargo.getObservacion());
        	cargoActual.setEstado(cargo.getEstado());
        	cargoActual.setIdUsuarioModifica(cargo.getIdUsuarioModifica());
        	cargoActual.setFechaModifica(new Date());

            cargoUpdate  = cargoService.save(cargoActual);

        }catch(DataAccessException e) {
            e.printStackTrace();
            response.put("mensaje", "Error al actualizar en la BD");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Cargo actualizado exitosamente");
        response.put("cargo", cargoUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/cargo/page")
    public Page<Cargo> index(
            @RequestParam(value = "nombre"						,required = false,defaultValue = "") 	String nombre,
            @RequestParam(value = "estadoId"					,required = false,defaultValue = "-1") 	Integer estadoId,
            @RequestParam(value = "columnSort"					,required = false,defaultValue = "id") 	String columnSort,
            @RequestParam(value = "order"						,required = false,defaultValue = "0") 	Integer order,
            @RequestParam(value = "pagina"						,required = false,defaultValue = "0") 	Integer page) {
        return cargoService.findAllPageAndSort(nombre, estadoId,columnSort,order,page);
    }
    
    @GetMapping("/cargo/autocomplete")
    public List<Cargo> autocomplete(
    		@RequestParam(value = "nombre"						,required = false,defaultValue = "") 	String nombre){
    	return cargoService.autocompleteList(nombre);
    }

}
