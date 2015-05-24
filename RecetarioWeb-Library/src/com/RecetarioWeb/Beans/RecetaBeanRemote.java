/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Receta;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Personal
 */
@Remote
public interface RecetaBeanRemote {

    void registrarReceta(Receta receta);

    void actualizarReceta(Receta receta);

    ArrayList findAll();

    Receta findByName(String nombrereceta);

    ArrayList findByFecha(String fecha);

    Receta findByIdUser(String iduserreceta);

    Receta findByIngredientes(String ingredientes);
    
}
