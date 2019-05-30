/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.controller;

import com.almacen.EJB.ProductoFacadeLocal;
import com.almacen.modelo.Producto;
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
public class ModificarProductosController implements Serializable{
    
    @EJB
    private ProductoFacadeLocal productoEJB;

    private List<Producto> listaProductos;

    @Inject
    private Producto productoSeleccionado;
    
    @PostConstruct
    public void init(){
        
        listaProductos = productoEJB.findAll();
        
    }
    
    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }
    
    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }
    
    public void establecerProducto(Producto prd){
        this.productoSeleccionado = prd;
    }
    
    public void actualizarProducto(){
        productoEJB.edit(productoSeleccionado);
    }
    
    public void eliminarProducto(){        
        productoEJB.remove(productoSeleccionado);
        listaProductos.remove(productoSeleccionado);
    }
}
