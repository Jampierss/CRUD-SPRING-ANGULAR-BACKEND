package com.tevology.logistica.models.entity.seguridad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "seg_menu")
public class Menu implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;		
	
	@Column(length = 100)
	private String nombre;

	@Column(length = 100)
	private String icon;
	
	@Column(length = 200)
	private String ruta;
	
    @OneToMany(mappedBy = "menu",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = {"menu","hibernateLazyInitializer", "handler"}, allowSetters = true)
    private List<RoleDetalle> rolesDetallados;
    
	@JsonIgnoreProperties(value = { "menus", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private SubModulo subModulo;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public List<RoleDetalle> getRolesDetallados() {
		return rolesDetallados;
	}

	public void setRolesDetallados(List<RoleDetalle> rolesDetallados) {
		this.rolesDetallados = rolesDetallados;
	}

	public SubModulo getSubModulo() {
		return subModulo;
	}

	public void setSubModulo(SubModulo subModulo) {
		this.subModulo = subModulo;
	}

	private static final long serialVersionUID = 1L;
}
