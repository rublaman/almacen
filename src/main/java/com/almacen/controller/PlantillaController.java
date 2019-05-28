/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.controller;

import com.almacen.EJB.MenuFacadeLocal;
import com.almacen.modelo.Menu;
import com.almacen.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author ruben
 */

@Named
@SessionScoped

public class PlantillaController implements Serializable{
    
    private MenuModel modelo;
    
    @EJB
    private MenuFacadeLocal menuEJB;
    
    @PostConstruct
    public void init(){
        this.cargarMenu();
        
    }
    
    public void comprobarPrivilegios(){
        
        Usuario us = new Usuario();
        
        try{
            us = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if(us==null){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/publico/error.xhtml?faces-redirect=true");
            } 
        }catch(Exception e){
            System.out.println("Error al redireccionar");
        }

    }
    
    public void cargarMenu(){
        
        modelo = new DefaultMenuModel();
        String contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        List<Menu> listaMenus;
        Usuario us = new Usuario();
        us = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        listaMenus = menuEJB.obtenerMenusUsuario(us);
        
        for (Menu m:listaMenus){
            if (m.getTipo().equals("S")){
                DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
                for (Menu sub:listaMenus){
                    if (sub.getMenu()!=null){
                        if(m.getIdMenu() == sub.getMenu().getIdMenu() && (sub.getTipo().equals("I"))){
                            DefaultMenuItem item = new DefaultMenuItem(sub.getNombre());
                            item.setUrl(contexto + sub.getUrl());
                            firstSubmenu.addElement(item);
                        }
                    }
                }
                modelo.addElement(firstSubmenu);
            } else {
                if (m.getMenu()==null){
                    DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                    item.setUrl(contexto + m.getUrl());
                    modelo.addElement(item);
                }
            }
        }
        
        
    }

    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }
    
    public void cerrarSesion(){
        
        try{
            String contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect(contexto + "/");  
        } catch(Exception e){
            System.out.println("Error al cerrar sesion" + e.getMessage());
        }
    }
    
}
