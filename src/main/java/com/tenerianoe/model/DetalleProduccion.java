/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "detalleproduccion")
public class DetalleProduccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleProduccion;
    @JoinColumn(name = "idProduccion", referencedColumnName = "idProduccion")
    @ManyToOne
    private Produccion idProduccion;
    @Column(name= "etapaProduccion")
    String etapaProduccion;
    @JoinColumn(name = "idCatalogoProducto", referencedColumnName = "idCatalogoProducto")
    @ManyToOne
    private catalogo_producto idCatalogoProducto;
    @Column(name = "cantidadProducto")
    private Double cantidadProduco;

    public Integer getIdDetalleProduccion() {
        return idDetalleProduccion;
    }

    public void setIdDetalleProduccion(Integer idDetalleProduccion) {
        this.idDetalleProduccion = idDetalleProduccion;
    }

    public Produccion getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(Produccion idProduccion) {
        this.idProduccion = idProduccion;
    }

    public catalogo_producto getIdCatalogoProducto() {
        return idCatalogoProducto;
    }

    public void setIdCatalogoProducto(catalogo_producto idCatalogoProducto) {
        this.idCatalogoProducto = idCatalogoProducto;
    }

    public Double getCantidadProducto() {
        return cantidadProduco;
    }

    public void setCantidadProducto(Double cantidadProduco) {
        this.cantidadProduco = cantidadProduco;
    }

    public String getEtapaProduccion() {
        return etapaProduccion;
    }

    public void setEtapaProduccion(String etapaProduccion) {
        this.etapaProduccion = etapaProduccion;
    }



   
    

}
