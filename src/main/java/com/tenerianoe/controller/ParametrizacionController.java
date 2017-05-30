/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.controller;

import com.tenerianoe.ejb.CatalogoProductoFacadeLocal;
import com.tenerianoe.ejb.EtapaFacadeLocal;
import com.tenerianoe.ejb.InsumosPorcentajeFacadeLocal;
import com.tenerianoe.model.catalogo_producto;
import com.tenerianoe.model.Etapa;
import com.tenerianoe.model.InsumosPorcentaje;
import com.tenerianoe.report.ReporteProveedor;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author admin
 */
@ManagedBean(name = "parametrizacionController")
@ViewScoped
public class ParametrizacionController implements Serializable {

    //OBJETOS
    private Etapa etapa;
    private catalogo_producto producto;
    private InsumosPorcentaje insumo;
    private Integer porcentaje;

    //EJBS
    @EJB
    private EtapaFacadeLocal etapaEJB;
    @EJB
    private CatalogoProductoFacadeLocal productoEJB;
    @EJB
    private InsumosPorcentajeFacadeLocal insumosEJB;

    //LISTAS
    private List<Etapa> listaEtapas;
    private List<catalogo_producto> listaProductos;
    private List<InsumosPorcentaje> listaInsumosPorcentajes;

    @PostConstruct
    public void init() {
        etapa = new Etapa();
        producto = new catalogo_producto();
        insumo = new InsumosPorcentaje();
        listaEtapas = etapaEJB.findAll();
        listaProductos = productoEJB.findAll();
        listaInsumosPorcentajes = insumosEJB.findAll();

    }

    public void asignarValores() {

        try {
            insumosEJB.create(insumo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Parametrizacion registrada"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error"));

        }
    }

    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //Instancia hacia la clase reporteClientes        
        ReporteProveedor rProveedor = new ReporteProveedor();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/reporteParametrizacion.jasper");

        rProveedor.getReporte(ruta);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    //Metodos para mostrar combos
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void enabledCombo() {
        enabled = true;
    }

    public void disabledButton() {
        enabled = false;
    }

    //Getter y Setters
    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public catalogo_producto getProducto() {
        return producto;
    }

    public void setProducto(catalogo_producto producto) {
        this.producto = producto;
    }

    public InsumosPorcentaje getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumosPorcentaje insumo) {
        this.insumo = insumo;
    }

    public List<Etapa> getListaEtapas() {
        return listaEtapas;
    }

    public void setListaEtapas(List<Etapa> listaEtapas) {
        this.listaEtapas = listaEtapas;
    }

    public List<catalogo_producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<catalogo_producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<InsumosPorcentaje> getListaInsumosPorcentajes() {
        return listaInsumosPorcentajes;
    }

    public void setListaInsumosPorcentajes(List<InsumosPorcentaje> listaInsumosPorcentajes) {
        this.listaInsumosPorcentajes = listaInsumosPorcentajes;
    }

}
