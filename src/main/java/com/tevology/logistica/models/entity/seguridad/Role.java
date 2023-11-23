package com.tevology.logistica.models.entity.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seg_rol")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, length = 20)
	private String nombre;

	@Column(length = 100, name="nombre_detallado")
	private String nombreDetallado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreDetallado() {
		return nombreDetallado;
	}

	public void setNombreDetallado(String nombreDetallado) {
		this.nombreDetallado = nombreDetallado;
	}
	
	private static final long serialVersionUID = 1L;	
}
