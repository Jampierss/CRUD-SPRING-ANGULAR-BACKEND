package com.tevology.logistica.models.entity.auxiliares;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tevology.logistica.models.entity.seguridad.SubModulo;

@Entity
@Table(name = "cfg_tabla_auxiliar")
public class TablaAuxiliar implements Serializable {

	@Id
	@Column(name = "cod_tabla_auxiliar",length = 6)
	@Size(max = 6)
	private String codTablaAuxiliar;

	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"subModulo", "hibernateLazyInitializer", "handler"}, allowSetters=true)		
	private SubModulo subModulo;
	
	@Column(name = "ind_edicion")
	private Boolean indEdicion;

	private String observacion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_tabla_auxiliar")
	@JsonIgnoreProperties({ "tablaAuxiliarDetalleId", "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private List<TablaAuxiliarDetalle> detalleAuxiliar;

	@Column(name = "id_usuario_crea")
	private Integer idUsuarioCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_crea")
	private Date fechaCrea;
	
	public TablaAuxiliar() {
		this.detalleAuxiliar = new ArrayList<TablaAuxiliarDetalle>();
	}
	
	@PrePersist
	public void prePersist() {
		fechaCrea = new Date();
	}

	public String getCodTablaAuxiliar() {
		return codTablaAuxiliar;
	}

	public void setCodTablaAuxiliar(String codTablaAuxiliar) {
		this.codTablaAuxiliar = codTablaAuxiliar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public SubModulo getSubModulo() {
		return subModulo;
	}

	public void setSubModulo(SubModulo subModulo) {
		this.subModulo = subModulo;
	}
	
	public Boolean getIndEdicion() {
		return indEdicion;
	}

	public void setIndEdicion(Boolean indEdicion) {
		this.indEdicion = indEdicion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<TablaAuxiliarDetalle> getDetalleAuxiliar() {
		return detalleAuxiliar;
	}

	public void setDetalleAuxiliar(List<TablaAuxiliarDetalle> detalleAuxiliar) {
		this.detalleAuxiliar = detalleAuxiliar;
	}

	public Integer getIdUsuarioCrea() {
		return idUsuarioCrea;
	}

	public void setIdUsuarioCrea(Integer idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	private static final long serialVersionUID = 1L;

}
