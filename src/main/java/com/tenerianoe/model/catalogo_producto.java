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
import javax.persistence.Table;

/**
 *
 * @author said
 */
@Entity
@Table(name = "catalogo_producto")
public class catalogo_producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCatalogoProducto;

    @Column(name = "producto")
    private String producto;
    @Column(name = "unidadMedida")
    private String unidadMedida;
    @Column(name = "stockActual")
    private Double stockActual;
    @Column(name = "stockMinimo")
    private Double stockMinimo;

    public int getIdCatalogoProducto() {
        return idCatalogoProducto;
    }

    public void setIdCatalogoProducto(Integer idCatalogoProducto) {
        this.idCatalogoProducto = idCatalogoProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Double getStockActual() {
        return stockActual;
    }

    public void setStockActual(Double stockActual) {
        this.stockActual = stockActual;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }


}
