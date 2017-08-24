package com.tenerianoe.controller;

import com.tenerianoe.ejb.CatalogoProductoFacadeLocal;
import com.tenerianoe.ejb.DetalleProduccionFacadeLocal;
import com.tenerianoe.ejb.InsumosPorcentajeFacadeLocal;
import com.tenerianoe.ejb.ProduccionFacadeLocal;
import com.tenerianoe.model.catalogo_producto;
import com.tenerianoe.model.DetalleProduccion;
import com.tenerianoe.model.InsumosPorcentaje;
import com.tenerianoe.model.Produccion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author admin
 */
@ManagedBean(name = "produccionController")
@ViewScoped
public class ProduccionController implements Serializable {

    //Objetos
    private Produccion produccion;
    private catalogo_producto producto;
    private Double cantidadProducto;
    private InsumosPorcentaje porcentajeInsumo;
    private DetalleProduccion detalleProduccion;

    //EJBS
    @EJB
    private CatalogoProductoFacadeLocal productoEJB;
    @EJB
    private InsumosPorcentajeFacadeLocal parametrizacionEJB;
    @EJB
    private ProduccionFacadeLocal produccionEJB;
    @EJB
    private DetalleProduccionFacadeLocal detalleProduccionEJB;

    //Listas
    private List<catalogo_producto> listaProductos;
    private List<InsumosPorcentaje> listarPocentajesInsumos;
    private List<Produccion> listarProduccion;
    private List<DetalleProduccion> listaDetallesProduccion;
    private List<DetalleProduccion> listaDetallesProduccionPorProceso;
    
    
    @PostConstruct
    public void init() {
        produccion = new Produccion();
        producto = new catalogo_producto();
        porcentajeInsumo = new InsumosPorcentaje();
        detalleProduccion = new DetalleProduccion();
        listaProductos = productoEJB.findAll();
        listarPocentajesInsumos = parametrizacionEJB.findAll();
        listarProduccion = produccionEJB.findAll();
        listaDetallesProduccion = detalleProduccionEJB.findAll();
        listaDetallesProduccionPorProceso = detalleProduccionEJB.listaDetallesPorProceso();
    }

    public void generarProcesoCuero() {

        try {
            produccionEJB.create(produccion);
            for (InsumosPorcentaje item : listarPocentajesInsumos) {
                detalleProduccion.setIdProduccion(produccion);
                detalleProduccion.setIdCatalogoProducto(item.getIdCatalogoProducto());
                detalleProduccion.setEtapaProduccion(item.getIdEtapa().getEtapa());
                detalleProduccion.setCantidadProducto((item.getPorcentaje() * produccion.getCantidadPiel()) / 100);
                detalleProduccionEJB.create(detalleProduccion);
            }

        } catch (Exception e) {
        }

        //Getter y Setters
    }

    public void obtenerUltimoNumeroProceso() {

        
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public catalogo_producto getProducto() {
        return producto;
    }

    public void setProducto(catalogo_producto producto) {
        this.producto = producto;
    }

    public Double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Double cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public InsumosPorcentaje getPorcentajeInsumo() {
        return porcentajeInsumo;
    }

    public void setPorcentajeInsumo(InsumosPorcentaje porcentajeInsumo) {
        this.porcentajeInsumo = porcentajeInsumo;
    }

    public DetalleProduccion getDetalleProduccion() {
        return detalleProduccion;
    }

    public void setDetalleProduccion(DetalleProduccion detalleProduccion) {
        this.detalleProduccion = detalleProduccion;
    }

    public List<catalogo_producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<catalogo_producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<InsumosPorcentaje> getListarPocentajesInsumos() {
        return listarPocentajesInsumos;
    }

    public void setListarPocentajesInsumos(List<InsumosPorcentaje> listarPocentajesInsumos) {
        this.listarPocentajesInsumos = listarPocentajesInsumos;
    }

    public List<Produccion> getListarProduccion() {
        return listarProduccion;
    }

    public void setListarProduccion(List<Produccion> listarProduccion) {
        this.listarProduccion = listarProduccion;
    }

    public List<DetalleProduccion> getListaDetallesProduccion() {
        return listaDetallesProduccion;
    }

    public void setListaDetallesProduccion(List<DetalleProduccion> listaDetallesProduccion) {
        this.listaDetallesProduccion = listaDetallesProduccion;
    }

    public List<DetalleProduccion> getListaDetallesProduccionPorProceso() {
        return listaDetallesProduccionPorProceso;
    }

    public void setListaDetallesProduccionPorProceso(List<DetalleProduccion> listaDetallesProduccionPorProceso) {
        this.listaDetallesProduccionPorProceso = listaDetallesProduccionPorProceso;
    }

}
