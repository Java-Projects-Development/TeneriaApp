package com.tenerianoe.controller;

import com.tenerianoe.report.ReporteProveedor;
import com.tenerianoe.ejb.CatalogoProveedorFacadeLocal;
import com.tenerianoe.model.CatalogoProveedor;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class ProveedorController implements Serializable {

    @EJB
    private CatalogoProveedorFacadeLocal proveedorEJB;

    private CatalogoProveedor proveedor;
    private List<CatalogoProveedor> proveedores;

    @PostConstruct
    public void init() {

        proveedor = new CatalogoProveedor();
        proveedores = proveedorEJB.findAll();

    }

    /**
     * Metodo para registrar un proveedor
     *
     */
    public void registrar() {
        try {
            proveedorEJB.create(proveedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Proveedor registrado con éxito"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }
    }

    public void modificar() {
        try {
            proveedorEJB.edit(proveedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Proveedor modificado con éxito"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }
    }

    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //Instancia hacia la clase reporteClientes        
        ReporteProveedor rProveedor = new ReporteProveedor();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/reporteProveedor.jasper");

        rProveedor.getReporte(ruta);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public CatalogoProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(CatalogoProveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<CatalogoProveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<CatalogoProveedor> proveedores) {
        this.proveedores = proveedores;
    }

}
