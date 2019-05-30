/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.EJB;

import com.almacen.modelo.Categoria;
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
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {
    @PersistenceContext(unitName = "AlmacenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    
    @Override
    public boolean existeCategoria(String nombreCategoria){
        boolean existe=false;
        String consulta;
        List<Categoria> listaDeCategorias;
        
        try 
        {
           consulta="FROM Categoria u WHERE u.nombreCategoria=?1";
           Query query=em.createQuery(consulta);
           query.setParameter(1, nombreCategoria);
           listaDeCategorias = query.getResultList();
           
           if(!listaDeCategorias.isEmpty()) existe=true;
           
        } catch (Exception e) 
        {
            System.out.println("No hemos podido consultar la base de datos");
        }
        
        return(existe);
    }
}
