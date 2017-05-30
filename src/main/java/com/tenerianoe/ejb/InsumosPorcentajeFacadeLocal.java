/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.ejb;

import com.tenerianoe.model.InsumosPorcentaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface InsumosPorcentajeFacadeLocal {

    void create(InsumosPorcentaje insumosPorcentaje);

    void edit(InsumosPorcentaje insumosPorcentaje);

    void remove(InsumosPorcentaje insumosPorcentaje);

    InsumosPorcentaje find(Object id);

    List<InsumosPorcentaje> findAll();

    List<InsumosPorcentaje> findRange(int[] range);

    int count();
    
}
