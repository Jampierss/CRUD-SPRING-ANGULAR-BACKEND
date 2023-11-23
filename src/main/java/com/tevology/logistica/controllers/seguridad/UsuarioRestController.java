package com.tevology.logistica.controllers.seguridad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tevology.logistica.VariablesGlobales;
import com.tevology.logistica.models.entity.seguridad.ChangedPassword;
import com.tevology.logistica.models.entity.seguridad.PasswordResetToken;
import com.tevology.logistica.models.entity.seguridad.Role;
import com.tevology.logistica.models.entity.seguridad.Usuario;
import com.tevology.logistica.models.response.seguridad.UsuarioResponse;
import com.tevology.logistica.models.services.IEmailService;
import com.tevology.logistica.models.services.auxiliares.IConfiguracionService;
import com.tevology.logistica.models.services.seguridad.IPasswordResetTokenService;
import com.tevology.logistica.models.services.seguridad.IRoleService;
import com.tevology.logistica.models.services.seguridad.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory.getLogger(UsuarioRestController.class);
	private static final Integer ITEMS_PER_PAGE = 10;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IPasswordResetTokenService passwordResetTokenService;
	
	@SuppressWarnings("unused")
	@Autowired
	private IConfiguracionService configurationService;
	
	@GetMapping("/role/all")
	public List<Role> getAllModulo() {
		return roleService.findAll();
	}
	
	@GetMapping("/role/{id}")
	public Role getAllModulo(@PathVariable Integer id){
		return roleService.findById(id);
	}
	
	@GetMapping("/usuarios")
	public List<UsuarioResponse> comboBox() {
		return usuarioService.listUsername();
	}
	
	@GetMapping("/usuarios/page/{page}")
	public Page<Usuario> index(@PathVariable Integer page){
		return usuarioService.findAllUsuarioPage(PageRequest.of(page, ITEMS_PER_PAGE));
	}
	
	@GetMapping("/usuarios/page2/{page}")
	public Page<UsuarioResponse> indexUsuResponse(@PathVariable Integer page){
		return usuarioService.findAllUsuarioPageResp(PageRequest.of(page, ITEMS_PER_PAGE));
	}
	
	@GetMapping("/roles/autocompleteRoles/{term}")
	public List<Role> autocompletado(@PathVariable String term) {
		if (term.equals("inexistente")) {
			term = "";
		}
		
		return usuarioService.autocompleteListRol(term);
	}

	@GetMapping("/usuarios/autocomplete/{nombre}")
	public List<UsuarioResponse> autocompletadoNombre(@PathVariable String nombre) {
		if (nombre.equals("inexistente")) {
			nombre = "";
		}
		return usuarioService.autocompleteList(nombre);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = usuarioService.findUsuarioById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (usuario == null) {
			response.put("mensaje", "El Usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK); 
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
		Usuario usuarioNew = null;
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
			
			Usuario userAux = usuarioService.findUsuarioByCorreo(usuario.getEmail(), 0);
			
			if(userAux != null) {
				response.put("mensaje", "El usuario " + userAux.getUsername() + " ya se encuentra registrado con el correo " + userAux.getEmail());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);				
			}
			
			usuario.setEnabled(usuario.getEstado().getTablaAuxiliarDetalleId().getId() == 1);			
			usuario.setPassword(usuario.getUsername());	
			
			usuarioNew = usuarioService.saveUsuario(usuario);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Usuario registrado exitosamente");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> updateUsuario(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Integer id) {
		Usuario usuarioActual = usuarioService.findUsuarioById(id);
		Usuario usuarioUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			Usuario userAux = usuarioService.findUsuarioByCorreo(usuario.getEmail(), usuario.getId());
			
			if(userAux != null) {
				response.put("mensaje", "El usuario " + userAux.getUsername() + " ya se encuentra registrado con el correo " + userAux.getEmail());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);				
			}
			
			
			Usuario userAux2 = usuarioService.findByUsername(usuario.getUsername());
			
			if(userAux2 != null && userAux2.getId() != usuario.getId()) {
				response.put("mensaje", "Ya existe un usuario registrado con el username " + userAux2.getUsername());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);				
			}
			
			usuarioActual.setUsername(usuario.getUsername());
			usuarioActual.setEmail(usuario.getEmail());
			usuarioActual.setRoles(usuario.getRoles());
			usuarioActual.setEnabled(usuario.getEstado().getTablaAuxiliarDetalleId().getId() == 1);
			usuarioActual.setEstado(usuario.getEstado());
			usuarioActual.setIdUsuarioModifica(usuario.getIdUsuarioModifica());
			usuarioActual.setFechaModifica(new Date());
			
			usuarioUpdate = usuarioService.saveUsuario(usuarioActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Centro de Costo actualizado exitosamente");
		response.put("usuario", usuarioUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody String userEmail) {
		
		Map<String, Object> response = new HashMap<>();
		
	    Usuario user = usuarioService.findUsuarioByCorreo(userEmail,0);
	    
	    if(user == null) {
	    	response.put("mensaje", "Error: el usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }
	      
	    String token = UUID.randomUUID().toString();
	    usuarioService.createPasswordResetTokenForUsuario(user, token);
	    
	    String content = "Estimado usuario " + user.getUsername() + ",\n"+
	    				 "Para cambiar su contraseña, " + 
	    				 "ingrese al siguiente link para restaurar su contraseña :"+ "\n"+
	    				 VariablesGlobales.RUTA_FRONTEND +"/user/changedPassword/"+token+"\n\n"+
	    				 "Gracias.";
	    		
	    try {
	        LocalDateTime date = LocalDateTime.now();
	        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");    	
	    	emailService.sendEmail(user.getEmail(), "Cambio de contraseña " + date.format(format), content, "", null, null);
	    
	    }catch(Exception e) {
	    	response.put("mensaje", "Error al enviar el correo");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    response.put("mensaje", "Se envió el correo exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	      
	}
	
	
	@GetMapping("/user/comprobar/{token}")
	public ResponseEntity<?> showChangePasswordPage(@PathVariable String token) {
		
		Map<String, Object> response = new HashMap<>();
	    String result = "El resultado es ";
	    PasswordResetToken passToken = usuarioService.findByToken(token);
	    
	    if(!isTokenValidado(passToken)) {
	    	result += ": token invalido";
	    	response.put("mensaje", result);
	 		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
	    } else {
	    	result += ": token valido";
	    	response.put("mensaje", result);
	 		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	    }	    
	}
	
	@PutMapping("/user/changedPassword/{token}/{password}")
	public ResponseEntity<?> update(@Valid @RequestBody ChangedPassword changedPassword, BindingResult result, 
			@PathVariable String token, @PathVariable String password) {
		
		PasswordResetToken passToken = usuarioService.findByToken(token);
		Usuario usuarioActual = null;
		Usuario usuarioUpdate = null;
		usuarioActual = usuarioService.findUsuarioById(passToken.getUser().getId());
				
		Map<String, Object> response = new HashMap<>();
		
		
		if(!isTokenValidado(passToken)) {
			response.put("mensaje", "Error, el token es invalido");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);			
		}
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			usuarioActual.setPassword(passwordEncoder.encode(password));
			usuarioActual.setEnabled(true);
			usuarioUpdate =  usuarioService.saveUsuario(usuarioActual);
			
			passToken.setEnabled(false);
			passToken.setUser(usuarioUpdate);
			passwordResetTokenService.save(passToken);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "ControlEnvioDetalle actualizada exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/user/password/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ChangedPassword changedPassword, BindingResult result, @PathVariable Integer id) {
		Usuario usuarioActual = usuarioService.findUsuarioById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			usuarioActual.setPassword(passwordEncoder.encode(changedPassword.getPassword()));
			
			usuarioService.saveUsuario(usuarioActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Datos actualizado exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/user/datos/{id}")
	public ResponseEntity<?> updateDatos(@Valid @RequestBody ChangedPassword changedPassword, BindingResult result, @PathVariable Integer id) {
		Usuario usuarioActual = usuarioService.findUsuarioById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			usuarioActual.setEmail(changedPassword.getCorreoNuevo());
			
			usuarioService.saveUsuario(usuarioActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Datos actualizado exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/validateUser")
	public ResponseEntity<?> validatePassword(@RequestBody Integer userId) {
		
		Map<String, Object> response = new HashMap<>();
		
	    Usuario user = usuarioService.findUsuarioById(userId);
	    		
	    if(user == null) {
	    	response.put("mensaje", "Error: el usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }
	      
	    String token = UUID.randomUUID().toString();
	    usuarioService.createPasswordResetTokenForUsuario(user, token);
	    
	    String content = "";
	    
	    content = "Hola "+user.getNombreCompleto()+"\n"
	 	    		+ "¡Felicitaciones! Su cuenta se creó exitosamente."+"\n"
	 	    		+ "Su nombre de usuario es: "+user.getUsername()+"\n"
	 	    		+ "Por favor de click sobre el enlace que figura a continuación para que usted genere su propia contraseña."+"\n"
	 	    		+ VariablesGlobales.RUTA_FRONTEND +"/user/validarUsuario/"+token+"\n"
	 	    		+ "\n"
	 	    		+ "Cordialmente"+ "\n"
	 	    		+ "Farmacia Universal";

	    try {
	    	emailService.sendEmail(user.getEmail(),"Correo electrónico de bienvenida", content,"", null, null);
	    }catch(Exception e) {
	    	response.put("mensaje", "Error al enviar el correo");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    response.put("mensaje", "Se envió el correo exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	      
	}
	
	@GetMapping("/user/validarUsuario/{token}")
	public ResponseEntity<?> showChangeValidationPasswordPage(@PathVariable String token) {
		
		Map<String, Object> response = new HashMap<>();
	    String result = "El resultado es ";
	    PasswordResetToken passToken = usuarioService.findByToken(token);
	    
	    if(!isTokenValidado(passToken)) {
	    	result += ": token invalido";
	    	log.info(result);
	    	response.put("mensaje", result);
	 		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
	    } else {
	    	result += ": token valido";
	    	log.info(result);
	    	response.put("mensaje", result);
	 		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	    }   
	}

	private boolean isTokenValidado(PasswordResetToken passToken) {
	    Date fecha = new Date();
		
	    if(passToken == null) {
	    	return false;
	    }
	    
		if(passToken.getUser() == null) {
	    	return false;
	    }	    
		
		if(passToken.getUser().getEnabled() == false) {
	    	return false;
	    }
		
		if(passToken.getExpiryDate().getTime() < fecha.getTime()) {
			return false;
		}
		
		if(passToken.getEnabled() == false) {
			return false;
		}
		
	    return true;
	}
}
