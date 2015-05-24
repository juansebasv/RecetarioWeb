/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Categoria;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Personal
 */
@Remote
public interface CetagoriaBeanRemote {

    void registrarCategoria(Categoria categoria);

    void actualizarCategoria(Categoria categoria);

    ArrayList findAll();

    Categoria findByName(String name);

}
