/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.ejb;

import com.tenerianoe.model.catalogo_producto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author admin
 */
@Stateless
public class CatalogoProductoFacade extends AbstractFacade<catalogo_producto> implements CatalogoProductoFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_TeneriaApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoProductoFacade() {
        super(catalogo_producto.class);
    }
    
}
