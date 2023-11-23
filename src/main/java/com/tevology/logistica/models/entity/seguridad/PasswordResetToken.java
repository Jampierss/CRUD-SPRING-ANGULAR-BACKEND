package com.tevology.logistica.models.entity.seguridad;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tevology.logistica.VariablesGlobales;

@Entity
@Table(name = "seg_password_reset_token")
public class PasswordResetToken {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String token;
 
    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Usuario user;
 
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    
    private boolean enabled;
    
    public PasswordResetToken(){}
    
    public PasswordResetToken(String token, Usuario usuario) {
    	this.token = token;
    	this.user = usuario;
    	this.enabled = true;
    }
    
    public PasswordResetToken(String token, Usuario usuario, Date expiryDate) {
    	this.token = token;
    	this.user = usuario;
    	this.expiryDate = expiryDate;
    	this.enabled = true;
    }
    
    @PrePersist
	public void prePersist() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.MINUTE, VariablesGlobales.MINUTES_EXPIRE_PASSWORD_RESET_TOKEN);
    	expiryDate = calendar.getTime();
    	enabled = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	} 
}
