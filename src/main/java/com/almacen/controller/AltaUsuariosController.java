/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.controller;

import com.almacen.EJB.PersonaFacadeLocal;
import com.almacen.EJB.RolFacadeLocal;
import com.almacen.EJB.UsuarioFacadeLocal;
import com.almacen.modelo.Persona;
import com.almacen.modelo.Rol;
import com.almacen.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ruben
 */

@Named
@ViewScoped
public class AltaUsuariosController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;
    @EJB
    private RolFacadeLocal rolEJB;
    
    @Inject
    private Usuario usuario;
    @Inject
    private Persona persona;
    @Inject
    private Rol rol;
    
    private List<String> listaDeTipos;
    private String tipoSeleccionado;
    private List<Rol> listaDeRoles;
    
    @PostConstruct
    public void init(){
       
        listaDeRoles = rolEJB.findAll();
        listaDeTipos = new <String>ArrayList();
        
        for(Rol r:listaDeRoles){
            listaDeTipos.add(r.getDescripcion());
        }
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<String> getListaDeTipos() {
        return listaDeTipos;
    }

    public void setListaDeTipos(List<String> listaDeTipos) {
        this.listaDeTipos = listaDeTipos;
    }

    public String getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(String tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }

    public List<Rol> getListaDeRoles() {
        return listaDeRoles;
    }

    public void setListaDeRoles(List<Rol> listaDeRoles) {
        this.listaDeRoles = listaDeRoles;
    }
    
    public void registrarUsuario(){
        try 
        {
            for(Rol r:listaDeRoles){
                if(r.getDescripcion().equals(tipoSeleccionado)) rol=r;
            }
            
            usuario.setRol(rol);
            usuario.setPersona(persona);
            if(!usuarioEJB.existeUsuario(usuario.getUser())){

                usuarioEJB.create(usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "usuario creado correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "usuario ya existe"));
            }
        } catch (Exception e) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "error al crear"));
        }
    }
}
