/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Receta;
import com.RecetarioWeb.Negocio.RecetaFacade;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Personal
 */
@Stateless
public class RecetaBean implements RecetaBeanRemote {

    @EJB
    private RecetaFacade recetaFacade;

    @Override
    public void registrarReceta(Receta receta) {
        recetaFacade.registrarReceta(receta);
    }

    @Override
    public void actualizarReceta(Receta receta) {
        recetaFacade.actualizarReceta(receta);
    }

    @Override
    public ArrayList findAll() {
        return recetaFacade.findAll();
    }

    @Override
    public Receta findByName(String nombrereceta) {
        return recetaFacade.findByName(nombrereceta);
    }

    @Override
    public ArrayList findByFecha(String fecha) {
        return recetaFacade.findByFecha(fecha);
    }

    @Override
    public Receta findByIdUser(String iduserreceta) {
        return recetaFacade.findByIdUser(iduserreceta);
    }

    @Override
    public Receta findByIngredientes(String ingredientes) {
        return recetaFacade.findByIngredientes(ingredientes);
    }

}
