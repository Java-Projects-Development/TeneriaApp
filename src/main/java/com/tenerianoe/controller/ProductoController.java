/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.controller;


import com.tenerianoe.ejb.CatalogoProductoFacadeLocal;
import com.tenerianoe.model.catalogo_producto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class ProductoController implements Serializable {

    @EJB
    private CatalogoProductoFacadeLocal productoEJB;

    private catalogo_producto producto;

    private List<catalogo_producto> productos;

    @PostConstruct
    public void init() {
        producto = new catalogo_producto();
        productos = productoEJB.findAll();

    }

    public void registrar() {
        try {
            productoEJB.create(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Producto registrado con éxito"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }

    }
    
        public void modificar() {
        try {
            productoEJB.edit(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Productro modificado con éxito"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }

    }

    //Getter y Setter
    public catalogo_producto getProducto() {
        return producto;
    }

    public void setProducto(catalogo_producto producto) {
        this.producto = producto;
    }

    public List<catalogo_producto> getProductos() {
        return productos;
    }

    public void setProductos(List<catalogo_producto> productos) {
        this.productos = productos;
    }

}
