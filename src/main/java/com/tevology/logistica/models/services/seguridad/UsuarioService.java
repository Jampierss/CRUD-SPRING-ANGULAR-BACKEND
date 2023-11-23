package com.tevology.logistica.models.services.seguridad;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tevology.logistica.models.dao.seguridad.IPasswordResetTokenDao;
import com.tevology.logistica.models.dao.seguridad.IUsuarioDao;
import com.tevology.logistica.models.entity.seguridad.PasswordResetToken;
import com.tevology.logistica.models.entity.seguridad.Role;
import com.tevology.logistica.models.entity.seguridad.Usuario;
import com.tevology.logistica.models.response.seguridad.UsuarioResponse;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IPasswordResetTokenDao passwordResetTokenDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario '" + username + "' en el sistema");
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioById(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioResponse> listUsername() {
		return usuarioDao.listUsername();
	}

	@Override
	public Page<Usuario> findAllUsuarioPage(Pageable pageable) {
		return usuarioDao.findAllPageUsers(pageable);
	}

	@Override
	public Page<UsuarioResponse> findAllUsuarioPageResp(Pageable pageable) {
		return usuarioDao.findAllUsuarioPageResp(pageable);
	}

	@Override
	public List<Role> autocompleteListRol(String term) {
		return usuarioDao.autocompleteListRol(term);
	}

	@Override
	public Usuario saveUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public void createPasswordResetTokenForUsuario(Usuario usuario, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, usuario);
		passwordResetTokenDao.save(myToken);
	}

	@Override
	public PasswordResetToken findByToken(String token) {
		return passwordResetTokenDao.findByToken(token);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioByCorreo(String correo, Integer user_id){
		return usuarioDao.findUserByCorreo(correo, user_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioResponse> autocompleteList(String nombre) {
		return usuarioDao.autocompleteList(nombre);
	}
}
