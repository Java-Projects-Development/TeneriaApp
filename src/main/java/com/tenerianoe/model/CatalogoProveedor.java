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
@Table(name = "catalogo_proveedor")
public class CatalogoProveedor implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCatalogoProveedor;
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "propietario")
    private String propietario;
    @Column(name = "nit")
    private String nit;
    @Column(name = "dui")
    private String dui;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;

    public int getIdCatalogoProveedor() {
        return idCatalogoProveedor;
    }

    public void setIdCatalogoProveedor(int idCatalogoProveedor) {
        this.idCatalogoProveedor = idCatalogoProveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public CatalogoProveedor() {
    }

    public CatalogoProveedor(Integer idCatalogoProveedor) {
        this.idCatalogoProveedor = idCatalogoProveedor;
    }

   
    public void setIdCatalogoProveedor(Integer idCatalogoProveedor) {
        this.idCatalogoProveedor = idCatalogoProveedor;
    }

        @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatalogoProveedor other = (CatalogoProveedor) obj;
        if (this.idCatalogoProveedor != other.idCatalogoProveedor) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idCatalogoProveedor;
        return hash;
    }

    @Override
    public String toString() {
        return "com.tenerianoe.model.CatalogoProveedor[ idCatalogoProveedor=" + idCatalogoProveedor + " ]";
    }

}
