/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.controller;

import com.almacen.EJB.UsuarioFacadeLocal;
import com.almacen.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
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

public class IndexController implements Serializable{
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @Inject
    private Usuario usuario;
    
    public String comprobarUsuario(){
        String direccion="";
        
       // usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        try {
            usuario = usuarioEJB.obtenerUsuario(usuario);
            
            if(usuario==null){
                direccion="publico/error.xhtml?faces-redirect=true";
            }else{
            
               direccion="privado/principal.xhtml?faces-redirect=true";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                
            }
                        
        } catch (Exception e) {
            System.out.println("Error al comprobar el usuario en la base de datos");
        }
        
        return direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
