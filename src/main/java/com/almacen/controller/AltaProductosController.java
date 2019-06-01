/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.controller;

import com.almacen.EJB.CategoriaFacadeLocal;
import com.almacen.EJB.PersonaFacadeLocal;
import com.almacen.EJB.ProductoFacadeLocal;
import com.almacen.EJB.RolFacadeLocal;
import com.almacen.EJB.UsuarioFacadeLocal;
import com.almacen.modelo.Categoria;
import com.almacen.modelo.Persona;
import com.almacen.modelo.Producto;
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
public class AltaProductosController implements Serializable{
    
    @EJB
    private ProductoFacadeLocal productoEJB;

    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @Inject
    private Producto producto;
    @Inject
    private Categoria categoria;
    
    private List<String> listaDeTipos;
    private String tipoSeleccionado;
    private List<Categoria> listaDeCategorias;
    
    @PostConstruct
    public void init(){
       
        listaDeCategorias = categoriaEJB.findAll();
        listaDeTipos = new <String>ArrayList();
        
        for(Categoria r:listaDeCategorias){
            listaDeTipos.add(r.getNombreCategoria());
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public List<Categoria> getListaDeCategorias() {
        return listaDeCategorias;
    }

    public void setListaDeCategorias(List<Categoria> listaDeCategorias) {
        this.listaDeCategorias = listaDeCategorias;
    }
    
    public void registrarProducto(){
        try 
        {
            for(Categoria r:listaDeCategorias){
                if(r.getNombreCategoria().equals(tipoSeleccionado)) categoria=r;
            }
            
            producto.setCategoria(categoria);

                productoEJB.create(producto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "producto creado correctamente"));
        } catch (Exception e) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "error al crear un producto"));
        }
    }
}
