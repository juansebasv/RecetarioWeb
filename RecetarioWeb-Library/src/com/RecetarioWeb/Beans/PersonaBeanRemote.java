/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Persona;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Personal
 */
@Remote
public interface PersonaBeanRemote {

    void registrarPersona(Persona persona);

    void actualizarPersona(Persona persona);

    ArrayList findAll();

    Persona findByCodigo(String code);

    Persona findByName(String name);

    Persona findByUsername(String username);

    ArrayList findByPais(String pais);

    ArrayList findByRol(String rol);

    Persona findByActivo(String activo);

    ArrayList findByCiudad(String ciudad);

    ArrayList findByFceha(String fecha);
    
}
