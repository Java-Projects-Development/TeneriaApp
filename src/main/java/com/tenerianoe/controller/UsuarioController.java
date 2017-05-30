package com.tenerianoe.controller;

import com.tenerianoe.ejb.PersonaFacadeLocal;
import com.tenerianoe.ejb.UsuarioFacadeLocal;
import com.tenerianoe.model.Persona;
import com.tenerianoe.model.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author said
 */
@Named
@ViewScoped

public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;

    private Usuario usuario;
    private Persona persona;

    private List<Persona> personas;
    private List<Usuario> usuarios;

    private String redireccion = null;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        persona = new Persona();
        personas = personaEJB.findAll();
        usuarios = usuarioEJB.findAll();

    }

    public void registrar() {
        try {
            this.usuario.setId_usuario(persona);
            String encriptar;
            encriptar = DigestUtils.sha1Hex(usuario.getClave());
            usuario.setClave(encriptar);
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario registrado con éxito"));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
        }
    }

    public void modificar() {
        try {
            personaEJB.edit(persona);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario modificado con éxito"));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }

    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    //Metodo para limpiar Factura
    public void limpiarModal() {
        this.persona = new Persona();
        this.usuario = new Usuario();
    }


}
