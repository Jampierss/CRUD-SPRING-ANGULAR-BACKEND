package com.tevology.logistica.models.entity.auxiliares;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cfg_tabla_auxiliar_detalle")
public class TablaAuxiliarDetalle implements Serializable {

	@EmbeddedId
	private TablaAuxiliarDetalleId tablaAuxiliarDetalleId;

	private String nombre;

	private String abreviatura;

	private String valor;

	private String valor2;

	private String observacion;
	
	@Column(name = "ind_habilitado")
	private Boolean indHabilitado;

	@Column(name = "id_usuario_crea")
	private Integer idUsuarioCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_crea")
	private Date fechaCrea;

	public TablaAuxiliarDetalleId getTablaAuxiliarDetalleId() {
		return tablaAuxiliarDetalleId;
	}

	public void setTablaAuxiliarDetalleId(TablaAuxiliarDetalleId tablaAuxiliarDetalleId) {
		this.tablaAuxiliarDetalleId = tablaAuxiliarDetalleId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}


	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public Boolean getIndHabilitado() {
		return indHabilitado;
	}

	public void setIndHabilitado(Boolean indHabilitado) {
		this.indHabilitado = indHabilitado;
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
