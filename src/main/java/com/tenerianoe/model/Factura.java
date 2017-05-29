/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author said
 */
@Entity
@Table(name = "factura")
public class Factura implements Serializable {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int idFactura;
      
      @ManyToOne
      @JoinColumn(name = "idCatalogoProveedor", referencedColumnName = "idCatalogoProveedor")
      private CatalogoProveedor proveedor;
      
      @Column(name = "numeroFactura")
      private String numeroFactura;
      
      @Column(name = "totalVenta")
      private BigDecimal totalVenta;
      
      @Temporal(TemporalType.TIMESTAMP)
      private Date fechaRegistro;

      public Factura() {
      }

      public int getIdFactura() {
            return idFactura;
      }

      public void setIdFactura(int idFactura) {
            this.idFactura = idFactura;
      }

      public String getNumeroFactura() {
            return numeroFactura;
      }

      public void setNumeroFactura(String numeroFactura) {
            this.numeroFactura = numeroFactura;
      }

      public BigDecimal getTotalVenta() {
            return totalVenta;
      }

      public void setTotalVenta(BigDecimal totalVenta) {
            this.totalVenta = totalVenta;
      }

      public Date getFechaRegistro() {
            return fechaRegistro;
      }

      public void setFechaRegistro(Date fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
      }

      public Factura(Integer idFactura) {
            this.idFactura = idFactura;
      }

      public CatalogoProveedor getProveedor() {
            return proveedor;
      }

      public void setProveedor(CatalogoProveedor proveedor) {
            this.proveedor = proveedor;
      }
      

      @Override
      public String toString() {
            return "com.tenerianoe.model.Factura[ idFactura=" + idFactura + " ]";
      }

}
