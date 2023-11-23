package com.tevology.logistica.models.dao.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tevology.logistica.models.entity.seguridad.SubModulo;

@Repository
public interface ISubModuloDao extends JpaRepository<SubModulo, Integer>{

	@Query(   "select mod.subModulos from Modulo mod "
			+ "where mod.id = ?1")
	public List<SubModulo> getSubModuloByIdModulo(Integer id);
	
}
