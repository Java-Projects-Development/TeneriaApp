/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.ejb;

import com.tenerianoe.model.Etapa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface EtapaFacadeLocal {

    void create(Etapa etapa);

    void edit(Etapa etapa);

    void remove(Etapa etapa);

    Etapa find(Object id);

    List<Etapa> findAll();

    List<Etapa> findRange(int[] range);

    int count();
    
}
