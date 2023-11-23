package com.tevology.logistica.controllers.maestros;

import com.tevology.logistica.models.entity.maestros.Subarea;
import com.tevology.logistica.models.services.maestros.ISubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SubareaController {

    @Autowired
    private ISubareaService subareaService;

    @GetMapping("/subarea/busqueda/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {

        Subarea subarea = null;
        Map<String, Object> response = new HashMap<>();

        try {
            subarea  = subareaService.findById(Long.valueOf(id));
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la BD");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Subarea>(subarea, HttpStatus.OK);
    }

    @GetMapping("/subarea/listado")
    public List<Subarea> all() { return subareaService.findAll(); }

    @PostMapping("/subarea")
    public ResponseEntity<?> create(@Valid @RequestBody Subarea subarea, BindingResult result) {
        Subarea subareaNew = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream().map(err -> "El campo'" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            subarea.setFechaCrea(new Date());
            subareaNew = subareaService.save(subarea);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar el insert en la Base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Mensaje", "Subarea registrado correctamente!");
        response.put("Subarea", subareaNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/subarea/update/{id}")
    public ResponseEntity<?> updateSubarea(@Valid @RequestBody Subarea subarea, BindingResult result, @PathVariable Integer id) {

        Subarea subareaActual = subareaService.findById(Long.valueOf(id));
        Subarea subareaUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream().map(fieldError -> "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        if (subareaActual == null) {
            response.put("Mensaje", "Error, no se pudo editar: El Subarea no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            subareaActual.setNombre(subarea.getNombre());
            subareaActual.setAbreviatura(subarea.getAbreviatura());
            subareaActual.setObservacion(subarea.getObservacion());
            subareaActual.setEstado(subarea.getEstado());
            subareaActual.setIdUsuarioModifica(subarea.getIdUsuarioModifica());
            subareaActual.setFechaModifica(new Date());

            subareaUpdate = subareaService.save(subareaActual);
        } catch (DataAccessException e) {
            e.printStackTrace();

            response.put("Mensaje", "Error al actualizar en la base de datos!");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Mensaje", "Subarea actualizado exitosamente!");
        response.put("Subarea", subareaUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/subarea/page")
    public Page<Subarea> index (
            @RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(name = "estadoId", required = false, defaultValue = "-1") Integer estadoId,
            @RequestParam(name = "columnSort", required = false, defaultValue = "id") String columnSort,
            @RequestParam(name = "order", required = false, defaultValue = "0") Integer order,
            @RequestParam(value = "pagina", required = false, defaultValue = "0") Integer page) {
        return subareaService.findAllPageAndSort(nombre, estadoId, columnSort, order, page);
    }

    @GetMapping("/subarea/autocomplete")
    public List<Subarea> autocomplete(@RequestParam(name = "nombre", required = false, defaultValue = "") String nombre) {
        return subareaService.autocompleteList(nombre);
    }


}
