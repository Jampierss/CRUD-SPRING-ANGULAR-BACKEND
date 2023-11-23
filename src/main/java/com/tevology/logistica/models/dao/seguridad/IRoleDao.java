package com.tevology.logistica.models.dao.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tevology.logistica.models.entity.seguridad.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Integer> {

}
