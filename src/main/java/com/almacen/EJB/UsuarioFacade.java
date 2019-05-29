/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.EJB;

import com.almacen.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ruben
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "AlmacenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public boolean existeUsuario(String nombreUsuario){
        boolean existe=false;
        String consulta;
        List<Usuario> listaDeUsuarios;
        
        try 
        {
           consulta="FROM Usuario u WHERE u.user=?1";
           Query query=em.createQuery(consulta);
           query.setParameter(1, nombreUsuario);
           listaDeUsuarios = query.getResultList();
           
           if(!listaDeUsuarios.isEmpty()) existe=true;
           
        } catch (Exception e) 
        {
            System.out.println("No hemos podido consultar la base de datos");
        }
        
        return(existe);
    }
    
    
    @Override
    public Usuario obtenerUsuario(Usuario usuario){
        
        Usuario user = null;
        List<Usuario> listaDeUsuarios;
        
        try {
            String consulta="FROM Usuario u WHERE u.user = ?1 and u.password = ?2";
            Query query=em.createQuery(consulta);
            query.setParameter(1, usuario.getUser());
            query.setParameter(2, usuario.getPassword());
            listaDeUsuarios = query.getResultList();
            if(!listaDeUsuarios.isEmpty()) {user = listaDeUsuarios.get(0);}
        } catch (Exception e) {
            System.out.println("Error desde el modelo al consultar la base de datos");
        }
        
        return user;
    }
    
}
