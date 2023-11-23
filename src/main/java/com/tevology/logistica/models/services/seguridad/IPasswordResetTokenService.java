package com.tevology.logistica.models.services.seguridad;

import com.tevology.logistica.models.entity.seguridad.PasswordResetToken;

public interface IPasswordResetTokenService {

	public PasswordResetToken findByToken(String token);
	public PasswordResetToken save(PasswordResetToken passwordResetToken);
}
