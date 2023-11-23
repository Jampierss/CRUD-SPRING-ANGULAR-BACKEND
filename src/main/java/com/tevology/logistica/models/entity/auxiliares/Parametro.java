package com.tevology.logistica.models.entity.auxiliares;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tevology.logistica.models.entity.seguridad.SubModulo;

@Entity
@Table(name = "cfg_parametro")
public class Parametro implements Serializable {

	@Id
	private Integer id;

	@Column(length = 100)
	private String nombre;

	@Column(length = 200)
	private String descripcion;

	@Column(length = 600)
	private String valor;

	private String tipo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "seg_submodulo_tevo_id", referencedColumnName = "id")
	private SubModulo submodulo;

	public SubModulo getSubModulo() {
		return submodulo;
	}

	public void setSubModulo(SubModulo submodulo) {
		this.submodulo = submodulo;
	}

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private static final long serialVersionUID = 1L;
}
