/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.controller;

import com.almacen.EJB.PersonaFacadeLocal;
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
public class ModificarPersonaController implements Serializable{
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    
   /* @EJB
    private UsuarioFacadeLocal usuarioEJB;*/
    
    private List<Persona> listaPersonas;
    
    @Inject
    private Persona personaSeleccionada;
    
    /*@Inject
    private Usuario usuarioSeleccionado;*/
    
    @PostConstruct
    public void init(){
        
        listaPersonas = personaEJB.findAll();
        
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }
    
    /*public Usuario getUsuarioSeleccionada() {
        usuarioSeleccionado = 
        return usuarioSeleccionado;
    }*/

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }
    
    public void establecerPersona(Persona per){
        this.personaSeleccionada = per;
    }
    
    public void actualizarPersona(){
        personaEJB.edit(personaSeleccionada);
    }
    
    public void eliminarPersona(){
       /* String consulta="FROM Usuario u WHERE u.idpersona = ?1 and u.password = ?2";
        
        usuarioEJB.remove(null);*/
        personaEJB.remove(personaSeleccionada);
    }
}
