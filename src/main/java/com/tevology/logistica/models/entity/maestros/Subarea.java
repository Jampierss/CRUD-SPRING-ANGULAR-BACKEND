package com.tevology.logistica.models.entity.maestros;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tevology.logistica.models.entity.auxiliares.TablaAuxiliarDetalle;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mae_subarea")
public class Subarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Area area;

    @Column(name = "abreviatura", length = 6)
    private String abreviatura;

    @Column(name = "observacion", length = 80)
    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
    private TablaAuxiliarDetalle estado;

    @Column(name = "id_usuario_crea", updatable = false)
    private Long idUsuarioCrea;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_crea", updatable = false)
    private Date fechaCrea;

    @Column(name = "id_usuario_modifica")
    private Long idUsuarioModifica;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modifica")
    private Date fechaModifica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public TablaAuxiliarDetalle getEstado() {
        return estado;
    }

    public void setEstado(TablaAuxiliarDetalle estado) {
        this.estado = estado;
    }

    public Long getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public void setIdUsuarioCrea(Long idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Long getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(Long idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
