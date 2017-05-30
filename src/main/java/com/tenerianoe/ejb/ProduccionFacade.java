/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.ejb;

import com.tenerianoe.model.Produccion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author admin
 */
@Stateless
public class ProduccionFacade extends AbstractFacade<Produccion> implements ProduccionFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_TeneriaApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduccionFacade() {
        super(Produccion.class);
    }

    @Override
    public Produccion obtenerNumeroProceso() {
    Produccion produccion= new Produccion();
    List<Produccion> lista= new ArrayList<>();
    
        try {
            Query query= em.createQuery("FROM Produccion p order by p.numeroProceso DESC");
            lista=(List<Produccion>) query.getResultList();
            
            if(lista!=null && lista.isEmpty()){
            produccion= lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return produccion;
    }

}
