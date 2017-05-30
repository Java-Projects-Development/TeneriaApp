/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "insumos_porcentaje")
public class InsumosPorcentaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInsumoPorcentaje;
    @JoinColumn(name = "idEtapa" )
    @ManyToOne
    private Etapa idEtapa;
    @JoinColumn(name = "idCatalogoProducto")
    @ManyToOne
    private catalogo_producto idCatalogoProducto;
    @Column(name = "porcentaje")
    private Integer porcentaje;

    public InsumosPorcentaje() {
    }
    
    


    
    

    public int getIdInsumoPorcetaje() {
        return idInsumoPorcentaje;
    }

    public void setIdInsumoPorcetaje(int idInsumoPorcetaje) {
        this.idInsumoPorcentaje = idInsumoPorcetaje;
    }

    public Etapa getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Etapa idEtapa) {
        this.idEtapa = idEtapa;
    }

    public catalogo_producto getIdCatalogoProducto() {
        return idCatalogoProducto;
    }

    public void setIdCatalogoProducto(catalogo_producto idCatalogoProducto) {
        this.idCatalogoProducto = idCatalogoProducto;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "InsumosPorcentaje{"+ idEtapa ;
    }



    
}
