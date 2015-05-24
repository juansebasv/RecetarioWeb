/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Categoria;
import com.RecetarioWeb.Negocio.CategoriaFacade;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Personal
 */
@Stateless
public class CetagoriaBean implements CetagoriaBeanRemote {

    @EJB
    private CategoriaFacade categoriaFacade;

    @Override
    public void registrarCategoria(Categoria categoria) {
        categoriaFacade.registrarCategoria(categoria);
    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
        categoriaFacade.actualizarCategoria(categoria);
    }

    @Override
    public ArrayList findAll() {
        return categoriaFacade.findAll();
    }

    @Override
    public Categoria findByName(String name) {
        return categoriaFacade.findByName(name);
    }

}
