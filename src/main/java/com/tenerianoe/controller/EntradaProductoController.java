/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.controller;


import com.tenerianoe.ejb.CatalogoProductoFacadeLocal;
import com.tenerianoe.ejb.CatalogoProveedorFacadeLocal;
import com.tenerianoe.ejb.DetalleFacturaFacadeLocal;
import com.tenerianoe.ejb.FacturaFacadeLocal;
import com.tenerianoe.model.CatalogoProveedor;
import com.tenerianoe.model.DetalleFactura;
import com.tenerianoe.model.Factura;
import com.tenerianoe.model.catalogo_producto;
import com.tenerianoe.report.ReporteProveedor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.ServletContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author said
 */
@ViewScoped
@ManagedBean(name = "entradaProductoController")
public class EntradaProductoController implements Serializable {

    private CatalogoProveedor proveedorSeleccionado;
    private catalogo_producto productoSeleccionado;
    private Double cantidadProducto;
    private Factura factura;
    private BigDecimal precioUnitario;
    private DetalleFactura detalleFactura;

    @EJB
    private DetalleFacturaFacadeLocal detalleEJB;
    private BigDecimal totalDetalle;

    @EJB
    private FacturaFacadeLocal facturaEJB;
    @EJB
    private CatalogoProveedorFacadeLocal proveedorEJB;
    @EJB
    private CatalogoProductoFacadeLocal productoEJB;

    private List<CatalogoProveedor> proveedores;
    private List<Factura> facturas;
    private List<DetalleFactura> listaDetalleFactura;

    @PostConstruct
    public void init() {

        proveedorSeleccionado = new CatalogoProveedor();
        productoSeleccionado = new catalogo_producto();
        factura = new Factura();
        proveedores = proveedorEJB.findAll();
        listaDetalleFactura = new ArrayList<>();
        detalleFactura = new DetalleFactura();
        facturas= facturaEJB.findAll();

    }

    public void modificar() {
        try {
            calcularTotalFacturaCompra();
            detalleEJB.edit(detalleFactura);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modifico"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se modifico!"));

        }
    }

    public void calcularTotal() {
        BigDecimal totalCompraPorProducto = detalleFactura.getPrecioUnitario().multiply(new BigDecimal(detalleFactura.getCantidad()));

    }

    public void guardarFactura() {
        facturaEJB.create(factura);
    }

    public void guardarDetalle() {
        try {

            if (detalleFactura.getCantidad() == 0 || detalleFactura.getPrecioUnitario().floatValue() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "EL valor ingresado es incorrecto"));
            } else {
                this.listaDetalleFactura.add(detalleFactura = new DetalleFactura(factura, productoSeleccionado, detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario(), BigDecimal.valueOf(detalleFactura.getPrecioUnitario().floatValue() * detalleFactura.getCantidad().floatValue())));
                this.detalleFactura = new DetalleFactura();
                this.productoSeleccionado = new catalogo_producto();
                //Calcula total de la venta
                calcularTotalFacturaCompra();
            }
        } catch (Exception e) {
        }
    }

    public void seleccion(CatalogoProveedor seleccion) {
        proveedorSeleccionado = seleccion;
    }

    public void handleKeyEvent() {
        FacesContext.getCurrentInstance().getAttributes().keySet().add(cantidadProducto);
    }

    public CatalogoProveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;

    }

    public void setProveedorSeleccionado(CatalogoProveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
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

    public catalogo_producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(catalogo_producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Double cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void onCellEditPrecio(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        detalleFactura.setPrecioUnitario((BigDecimal) event.getNewValue());

    }

    public void onCellEditCantidad(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        detalleFactura.setCantidad((Double) event.getNewValue());

    }

    public void generarTotal() {

        this.detalleFactura.setTotalCompra(this.detalleFactura.getPrecioUnitario().multiply(new BigDecimal(this.detalleFactura.getCantidad().floatValue())));

    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    
    public void calcularTotalFacturaCompra() {
        BigDecimal totalFacturaCompra = new BigDecimal(0);

        try {
            for (DetalleFactura item : listaDetalleFactura) {
                BigDecimal totalCompraPorProducto = item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad()));
                item.setTotalCompra(totalCompraPorProducto);
                totalFacturaCompra = totalFacturaCompra.add(totalCompraPorProducto);
                this.factura.setTotalVenta(totalFacturaCompra);
            }
            this.factura.setTotalVenta(totalFacturaCompra);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void quitarProductoDetalleFactura() {
        detalleEJB.remove(detalleFactura);
        this.calcularTotalFacturaCompra();

    }

    //Metodo para limpiar Factura
    public void limpiarFactura() {
        this.proveedorSeleccionado = new CatalogoProveedor();
        this.factura = new Factura();
        this.listaDetalleFactura = new ArrayList<>();
        this.totalDetalle = null;
        this.productoSeleccionado = new catalogo_producto();
    }

    //Metodo para guardar Factura de compra
    public void guardarCompra() {
        try {
            this.guardarFactura();
            for (DetalleFactura item : listaDetalleFactura) {
                detalleEJB.create(item);
                productoSeleccionado = item.getIdCatalogoProducto();
                productoSeleccionado.setStockActual(productoSeleccionado.getStockActual() + item.getCantidad());
                productoEJB.edit(productoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Factura de Compra registrada con éxito"));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            }
        } catch (Exception e) {
        }
    }

    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //Instancia hacia la clase reporteClientes        
        ReporteProveedor rProveedor = new ReporteProveedor();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/facturaCompra.jasper");

        rProveedor.getReporte2(ruta,factura.getNumeroFactura());
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void facturaReporte(Factura factura1 ) throws SQLException, ClassNotFoundException,InstantiationException, IllegalAccessException{
    //Instancia hacia la clase reporteClientes        
        ReporteProveedor rProveedor = new ReporteProveedor();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/facturaCompra.jasper");

        rProveedor.getReporte2(ruta,factura1.getNumeroFactura());
        FacesContext.getCurrentInstance().responseComplete();
    
    }

}
