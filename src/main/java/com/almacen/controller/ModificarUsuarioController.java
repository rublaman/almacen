/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.controller;

import com.almacen.EJB.UsuarioFacadeLocal;
import com.almacen.modelo.Persona;
import com.almacen.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ruben
 */

@Named
@ViewScoped
public class ModificarUsuarioController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    private List<Usuario> listaUsuarios;

    @Inject
    private Usuario usuarioSeleccionado;
    
    @PostConstruct
    public void init(){
        
        listaUsuarios = usuarioEJB.findAll();
        
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }
    
    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    public void establecerUsuario(Usuario usr){
        this.usuarioSeleccionado = usr;
    }
    
    public void actualizarUsuario(){
        usuarioEJB.edit(usuarioSeleccionado);
    }
    
    public void eliminarUsuario(){        
        usuarioEJB.remove(usuarioSeleccionado);
        listaUsuarios.remove(usuarioSeleccionado);
    }
}
