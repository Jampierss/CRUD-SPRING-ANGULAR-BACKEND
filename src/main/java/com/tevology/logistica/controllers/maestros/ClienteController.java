package com.tevology.logistica.controllers.maestros;

import static com.tevology.logistica.VariablesGlobales.DIRECTORIO_ARCHIVOS;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import com.tevology.logistica.models.entity.maestros.Cliente;
import com.tevology.logistica.models.services.IUploadFileService;
import com.tevology.logistica.models.services.maestros.IClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadService;
	
    @GetMapping("/cliente/busqueda/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
            cliente  = clienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la BD");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("mensaje", "El cliente no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }
    
    @GetMapping("/cliente/listadoSelect")
    public List<Cliente> all() {
        return clienteService.findAll();
    }
    
    @PostMapping("/cliente")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente clienteNew = null;

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
        	cliente.setFechaCrea(new Date());
        	clienteNew = clienteService.save(cliente);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la BD");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente registrado exitosamente");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/cliente/update/{id}")
    public ResponseEntity<?> updateCliente(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Integer id){

        Cliente clienteActual = clienteService.findById(id);
        Cliente clienteUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(clienteActual == null) {
            response.put("mensaje", "Error, no se pudo editar: El Cliente no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
        	
        	clienteActual.setTipoDocumentoIdentidad(cliente.getTipoDocumentoIdentidad());
        	clienteActual.setNroDocumentoIdentidad(cliente.getNroDocumentoIdentidad());
        	clienteActual.setRazonSocial(cliente.getRazonSocial());
        	clienteActual.setNombreComercial(cliente.getNombreComercial());
        	clienteActual.setAbreviatura(cliente.getAbreviatura());
        	clienteActual.setDireccion(cliente.getDireccion());
        	clienteActual.setCargo(cliente.getCargo());
        	clienteActual.setEstado(cliente.getEstado());
        	clienteActual.setIdUsuarioModifica(cliente.getIdUsuarioModifica());
        	clienteActual.setFechaModifica(new Date());
            clienteUpdate  = clienteService.save(clienteActual);

        }catch(DataAccessException e) {
            e.printStackTrace();
            response.put("mensaje", "Error al actualizar en la BD");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Cliente actualizado exitosamente");
        response.put("cliente", clienteUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/cliente/page")
    public Page<Cliente> index(
            @RequestParam(value = "columnSort"					,required = false,defaultValue = "id") 	String columnSort,
            @RequestParam(value = "order"						,required = false,defaultValue = "0") 	Integer order,
            @RequestParam(value = "pagina"						,required = false,defaultValue = "0") 	Integer page) {
        return clienteService.findAllPageAndSort(columnSort,order,page);
    }
    
    @PostMapping("/cliente/upload")
    public ResponseEntity<?> uploadFiles(	@RequestParam(value="file", required = false) MultipartFile file,
                                        	@RequestParam(value="id",  required = true) Integer id){
    	Map<String,Object> response = new HashMap<>();
    	Cliente cliente = clienteService.findById(id);
    	
        if (file != null && !file.isEmpty()) {
            String nombreArchivo1 = null;
            try {
                nombreArchivo1 = uploadService.copiar(file,DIRECTORIO_ARCHIVOS);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la foto");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            cliente.setFoto(nombreArchivo1);
        }
        
        clienteService.save(cliente);
        response.put("cliente", cliente);
        response.put("mensaje", "Has subido correctamente los archivos");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
    
    @GetMapping("/cliente/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Resource recurso = null;
        try {
            recurso = uploadService.cargar(nombreFoto,DIRECTORIO_ARCHIVOS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
}
