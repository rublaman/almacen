/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.controller;

import com.almacen.EJB.CategoriaFacadeLocal;
import com.almacen.modelo.Categoria;
import com.almacen.modelo.Rol;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

/**
 *
 * @author ruben
 */


@Named
@ViewScoped
public class AltaCategoriaController implements Serializable {
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    private Categoria categoria;
    
    @PostConstruct
    private void init(){
        categoria = new Categoria();
    }
    
    public void crearCategoria(){
        
        try 
        {
            if(!categoriaEJB.existeCategoria(categoria.getNombreCategoria())){

                categoriaEJB.create(categoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Categoria creada correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "Categoria ya existe"));
            }
        } catch (Exception e) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "error al crear"));
        }
        
    }

    public CategoriaFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriaFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }  
    
}
