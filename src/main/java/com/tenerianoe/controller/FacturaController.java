/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.controller;

import com.tenerianoe.ejb.CatalogoProductoFacadeLocal;
import com.tenerianoe.ejb.CatalogoProveedorFacadeLocal;
import com.tenerianoe.ejb.DetalleFacturaFacade;
import com.tenerianoe.ejb.FacturaFacadeLocal;
import com.tenerianoe.model.catalogo_producto;
import com.tenerianoe.model.CatalogoProveedor;
import com.tenerianoe.model.DetalleFactura;
import com.tenerianoe.model.Factura;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class FacturaController {

      //Objetos 
      private CatalogoProveedor proveedorSeleccionado;
      private catalogo_producto productoSeleccionado;
      private Factura factura;
      private DetalleFactura detalleFactura;

      //EJBS
      @EJB
      private FacturaFacadeLocal facturaEJB;
      @EJB
      private CatalogoProveedorFacadeLocal proveedorEJB;
      @EJB
      private CatalogoProductoFacadeLocal productoEJB;
      @EJB
      private DetalleFacturaFacade detalleEJB;

      //Listas
      private List<CatalogoProveedor> proveedores;
      private List<DetalleFactura> listaDetalleFactura;

      @PostConstruct
      public void init() {

            proveedorSeleccionado = new CatalogoProveedor();
            productoSeleccionado = new catalogo_producto();
            factura = new Factura();
            proveedores = proveedorEJB.findAll();
            listaDetalleFactura = new ArrayList<>();
            detalleFactura = new DetalleFactura();

      }

      public void obtenerProveedorSeleccionado(SelectEvent event) {
            proveedorSeleccionado = ((CatalogoProveedor) event.getObject());

      }

      public void obtenerProductoSeleccionado(SelectEvent event) {
            productoSeleccionado = ((catalogo_producto) event.getObject());

      }

      public void guardarDetalle() {
            try {

                  if (detalleFactura.getCantidad() == 0 || detalleFactura.getPrecioUnitario().floatValue() == 0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "EL valor ingresado es incorrecto"));
                  } else {

                        this.detalleFactura = new DetalleFactura();
                        this.productoSeleccionado = new catalogo_producto();
                        //Calcula total de la venta
                        calcularTotalFacturaCompra();
                  }
            } catch (Exception e) {
            }
      }

      //calculo el total de la compra y el total por cada detalle
      public void calcularTotalFacturaCompra() {
            BigDecimal totalFacturaCompra = new BigDecimal(0);

            try {
                  for (DetalleFactura item : listaDetalleFactura) {
                        BigDecimal totalCompraPorProducto = item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad()));
                        item.setTotalCompra(totalCompraPorProducto);
                        totalFacturaCompra = totalFacturaCompra.add(totalCompraPorProducto);
                  }
                  this.factura.setTotalVenta(totalFacturaCompra);
            } catch (Exception e) {
                  System.out.println(e.getMessage());
            }

      }

      //Metodo para guardar Factura de compra
      public void guardarCompra() {
            try {
                  for (DetalleFactura item : listaDetalleFactura) {
                        detalleEJB.create(item);
                  }
            } catch (Exception e) {
            }
      }

      //Metodo para limpiar Factura
      public void limpiarFactura() {
            this.proveedorSeleccionado = new CatalogoProveedor();
            this.factura = new Factura();
            this.listaDetalleFactura = new ArrayList<>();
            this.productoSeleccionado = new catalogo_producto();
      }

      //Getter y setters
      public CatalogoProveedor getProveedorSeleccionado() {
            return proveedorSeleccionado;
      }

      public void setProveedorSeleccionado(CatalogoProveedor proveedorSeleccionado) {
            this.proveedorSeleccionado = proveedorSeleccionado;
      }

      public catalogo_producto getProductoSeleccionado() {
            return productoSeleccionado;
      }

      public void setProductoSeleccionado(catalogo_producto productoSeleccionado) {
            this.productoSeleccionado = productoSeleccionado;
      }

      public Factura getFactura() {
            return factura;
      }

      public void setFactura(Factura factura) {
            this.factura = factura;
      }

      public DetalleFactura getDetalleFactura() {
            return detalleFactura;
      }

      public void setDetalleFactura(DetalleFactura detalleFactura) {
            this.detalleFactura = detalleFactura;
      }

      public List<CatalogoProveedor> getProveedores() {
            return proveedores;
      }

      public void setProveedores(List<CatalogoProveedor> proveedores) {
            this.proveedores = proveedores;
      }

      public List<DetalleFactura> getListaDetalleFactura() {
            return listaDetalleFactura;
      }

      public void setListaDetalleFactura(List<DetalleFactura> listaDetalleFactura) {
            this.listaDetalleFactura = listaDetalleFactura;
      }

}
