/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Negocio;

import com.RecetarioWeb.Entitys.Persona;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author Personal
 */
@Singleton
@LocalBean
public class Usuario {

    private static Persona persona = new Persona();

    public void guardarUsuario(Persona user){
        persona = user;
    }
    
    public static Persona getPersona() {
        return persona;
    }

    public static void setPersona(Persona persona) {
        Usuario.persona = persona;
    }
    
}
