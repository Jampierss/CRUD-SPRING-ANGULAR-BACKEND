package com.tevology.logistica.models.response.seguridad;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.tevology.logistica.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.tevology.logistica.models.entity.seguridad.Role;
import com.tevology.logistica.models.entity.seguridad.Usuario;

@Projection(
		  name = "usuarioResponse", 
		  types = { Usuario.class })
public interface UsuarioResponse {

	Integer getId();
	
	String getNombreCompleto();

	String getUsername();

	Boolean getEnabled();
	
	String getEmail();

	List<Role> getRoles();
	
	TablaAuxiliarDetalle getEstado();
	
	Integer getIdUsuarioCrea();

	Date getFechaCrea();
	
	Integer getIdUsuarioModifica();
	
	Date getFechaModifica();
}
