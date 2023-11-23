package com.tevology.logistica.models.services.seguridad;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tevology.logistica.models.entity.seguridad.PasswordResetToken;
import com.tevology.logistica.models.entity.seguridad.Role;
import com.tevology.logistica.models.entity.seguridad.Usuario;
import com.tevology.logistica.models.response.seguridad.UsuarioResponse;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public Usuario findUsuarioById(Integer id);
	
	public List<UsuarioResponse> listUsername();
	
	public Page<Usuario> findAllUsuarioPage(Pageable pageable);
	
	public Page<UsuarioResponse> findAllUsuarioPageResp(Pageable pageable);
	
	public List<Role> autocompleteListRol(String term);
	
	public Usuario saveUsuario(Usuario usuario);
	
	public void createPasswordResetTokenForUsuario(Usuario ususario, String token);
	
	public PasswordResetToken findByToken(String token);
	
	public Usuario findUsuarioByCorreo(String correo, Integer user_id);

	public List<UsuarioResponse> autocompleteList(String nombre);
}
