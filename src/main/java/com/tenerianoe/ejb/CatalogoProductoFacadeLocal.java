/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.ejb;

import com.tenerianoe.model.catalogo_producto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface CatalogoProductoFacadeLocal {

    void create(catalogo_producto catalogoProducto);

    void edit(catalogo_producto catalogoProducto);

    void remove(catalogo_producto catalogoProducto);

    catalogo_producto find(Object id);

    List<catalogo_producto> findAll();

    List<catalogo_producto> findRange(int[] range);

    int count();
    
}
