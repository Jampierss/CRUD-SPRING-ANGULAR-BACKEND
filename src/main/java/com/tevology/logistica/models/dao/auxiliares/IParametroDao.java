package com.tevology.logistica.models.dao.auxiliares;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tevology.logistica.models.entity.auxiliares.Parametro;

public interface IParametroDao extends JpaRepository<Parametro, Integer> {
	
	@Query("select p from Parametro p where p.id = 1")
	public Parametro findIntervaloHorario();
}
