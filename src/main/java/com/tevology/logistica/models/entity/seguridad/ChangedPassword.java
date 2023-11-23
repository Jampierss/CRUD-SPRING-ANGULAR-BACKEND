package com.tevology.logistica.models.entity.seguridad;

public class ChangedPassword {

	private String token;
	
	private String password;
	
	private String correo;
	
	private String correoNuevo;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCorreoNuevo() {
		return correoNuevo;
	}

	public void setCorreoNuevo(String correoNuevo) {
		this.correoNuevo = correoNuevo;
	}	
}
