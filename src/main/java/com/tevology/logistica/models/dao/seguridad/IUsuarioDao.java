package com.tevology.logistica.models.dao.seguridad;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tevology.logistica.models.entity.seguridad.Role;
import com.tevology.logistica.models.entity.seguridad.Usuario;
import com.tevology.logistica.models.response.seguridad.UsuarioResponse;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {
	
	public Usuario findByUsername(String username);
	
	@Query("SELECT u from Usuario u where u.id != 1")
	public List<UsuarioResponse> listUsername();
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.id != 1")
	public Page<Usuario> findAllPageUsers(Pageable pageable);
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.id != 1")
	public Page<UsuarioResponse> findAllUsuarioPageResp(Pageable pageable);

	@Query("select r from Role r where UPPER(r.nombreDetallado) like CONCAT('%',UPPER(?1),'%')")
	public List<Role> autocompleteListRol(String term);
	
	@Query("select u from Usuario u where u.email = ?1 and u.id != ?2")
	public Usuario findUserByCorreo(String correo, Integer user_id);

	@Query(value = "select TOP(5) * from seg_usuario u "
			+ "where UPPER(u.nombre_completo ) like UPPER('%' + :nombre + '%')", nativeQuery = true)
	public List<UsuarioResponse> autocompleteList(String nombre);
}
