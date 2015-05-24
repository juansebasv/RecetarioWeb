/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Negocio.PersonaFacade;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Personal
 */
@Stateless
public class PersonaBean implements PersonaBeanRemote {

    @EJB
    private PersonaFacade personaFacade;

    @Override
    public void registrarPersona(Persona persona) {
        personaFacade.registrarPersona(persona);
    }

    @Override
    public void actualizarPersona(Persona persona) {
        personaFacade.actualizarPersona(persona);
    }

    @Override
    public ArrayList findAll() {
        return personaFacade.findAll();
    }

    @Override
    public Persona findByCodigo(String code) {
        return personaFacade.findByCodigo(code);
    }

    @Override
    public Persona findByName(String name) {
        return personaFacade.findByName(name);
    }

    @Override
    public Persona findByUsername(String username) {
        return personaFacade.findByUsername(username);
    }

    @Override
    public ArrayList findByPais(String pais) {
        return personaFacade.findByPais(pais);
    }

    @Override
    public ArrayList findByRol(String rol) {
        return personaFacade.findByRol(rol);
    }

    @Override
    public Persona findByActivo(String activo) {
        return personaFacade.findByCodigo(activo);
    }

    @Override
    public ArrayList findByCiudad(String ciudad) {
        return personaFacade.findByCiudad(ciudad);
    }

    @Override
    public ArrayList findByFceha(String fecha) {
        return personaFacade.findByFecha(fecha);
    }

}
