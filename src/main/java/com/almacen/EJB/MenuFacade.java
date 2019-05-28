/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.EJB;

import com.almacen.modelo.Menu;
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
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {
    @PersistenceContext(unitName = "AlmacenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    
    @Override
    public List<Menu> obtenerMenusUsuario(Usuario us){
        List<Menu> listaMenus = null;
        try {
            String consulta="FROM Menu m WHERE m.rol.idRol=?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getRol().getIdRol());
            listaMenus = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al consultar los menus de un determinado usuario" + e.getMessage());
        }
        return(listaMenus);
    }
    
}
