/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Negocio.TipFacade;
import com.RecetarioWeb.Negocio.Usuario;
import com.RecetarioWeb.Negocio.UsuarioRemote;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author Personal
 */
@Singleton
public class UsuarioBean implements UsuarioBeanRemote {
    @EJB
    private Usuario usuario;
    
    @Override
    public void guardarUsuario(Persona user) {
        usuario.guardarUsuario(user);
    }
    
}
