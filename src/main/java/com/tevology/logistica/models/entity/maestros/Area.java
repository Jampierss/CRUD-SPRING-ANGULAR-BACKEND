package com.tevology.logistica.models.entity.maestros;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mae_area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 60)
    private String nombre;


    @Column(name = "abreviatura", length = 6)
    private String abreviatura;

    @Column(name = "observacion", length = 80)
    private String observacion;

    @Column(name = "estado")
    private Boolean estado;

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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
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
}
