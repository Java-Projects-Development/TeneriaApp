/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.ejb;

import com.tenerianoe.model.DetalleProduccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface DetalleProduccionFacadeLocal {

    void create(DetalleProduccion detalleProduccion);

    void edit(DetalleProduccion detalleProduccion);

    void remove(DetalleProduccion detalleProduccion);

    DetalleProduccion find(Object id);

    List<DetalleProduccion> findAll();

    List<DetalleProduccion> findRange(int[] range);

    int count();
    
      List<DetalleProduccion> listaDetallesPorProceso();
}
