/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "produccion")
public class Produccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduccion;
    @Column(name = "numeroProceso")
    private Integer numeroProceso;
    @Column(name = "tipoCuero")
    private String tipoCuero;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaInicio")
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaFinalizacion")
    private Date fechaFinalizacion;
    @Column(name = "tipoPiel")
    private String tipoPiel;
    @Column(name = "procedenciaPiel")
    private String procedenciaPiel;
    @Column(name = "cantidadPiel")
    private Double cantidadPiel;
    @Column(name = "grosorPiel")
    private Double grosorPiel;

    public Integer getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(Integer idProduccion) {
        this.idProduccion = idProduccion;
    }

    public Integer getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(Integer numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public String getTipoCuero() {
        return tipoCuero;
    }

    public void setTipoCuero(String tipoCuero) {
        this.tipoCuero = tipoCuero;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getTipoPiel() {
        return tipoPiel;
    }

    public void setTipoPiel(String tipoPiel) {
        this.tipoPiel = tipoPiel;
    }

    public String getProcedenciaPiel() {
        return procedenciaPiel;
    }

    public void setProcedenciaPiel(String procedenciaPiel) {
        this.procedenciaPiel = procedenciaPiel;
    }

    public Double getCantidadPiel() {
        return cantidadPiel;
    }

    public void setCantidadPiel(Double cantidadPiel) {
        this.cantidadPiel = cantidadPiel;
    }

    public Double getGrosorPiel() {
        return grosorPiel;
    }

    public void setGrosorPiel(Double grosorPiel) {
        this.grosorPiel = grosorPiel;
    }

}
