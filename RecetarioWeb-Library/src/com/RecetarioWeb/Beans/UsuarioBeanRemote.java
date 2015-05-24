/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Persona;
import javax.ejb.Remote;

/**
 *
 * @author Personal
 */
@Remote
public interface UsuarioBeanRemote {

    void guardarUsuario(Persona user);
    
}
