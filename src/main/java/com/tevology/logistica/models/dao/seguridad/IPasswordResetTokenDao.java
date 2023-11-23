package com.tevology.logistica.models.dao.seguridad;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tevology.logistica.models.entity.seguridad.PasswordResetToken;

public interface IPasswordResetTokenDao extends CrudRepository<PasswordResetToken, Long> {
	
	@Query("select u from PasswordResetToken u where u.token = ?1")
	public PasswordResetToken findByToken(String token);

}
