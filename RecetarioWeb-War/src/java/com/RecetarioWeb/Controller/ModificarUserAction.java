/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Controller.support.EjbLocator;
import com.RecetarioWeb.Controller.support.Results;
import com.RecetarioWeb.Entitys.Persona;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Edicion de los datos de contacto del usuario autenticado. Cada campo solo se
 * actualiza si llega con un minimo de longitud razonable.
 *
 * @author Personal
 */
public class ModificarUserAction extends ActionSupport {

    private static final Logger LOG = Logger.getLogger(ModificarUserAction.class.getName());

    private static final int MIN_EMAIL_LENGTH = 10;
    private static final int MIN_DIRECCION_LENGTH = 5;
    private static final int MIN_PAIS_LENGTH = 4;

    private final PersonaBeanRemote personaBean = EjbLocator.lookup(PersonaBeanRemote.class);
    private final Client client = Client.getInstace();
    private Persona persona;
    private String email;
    private String direccion;
    private String pais;
    private String ciudad;

    public ModificarUserAction() {
        persona = personaBean.findByUsername(client.getNickname());
    }

    @Override
    public String execute() throws Exception {
        if (persona == null) {
            LOG.log(Level.WARNING, "Modificacion de datos sin sesion valida (nickname=''{0}'')",
                    client.getNickname());
            return Results.FAILED;
        }
        if (hasErrors()) {
            return Results.ko(persona.getRol());
        }
        cargarObject();
        personaBean.actualizarPersona(persona);
        LOG.log(Level.INFO, "Datos de contacto actualizados para ''{0}''", client.getNickname());
        return Results.ok(persona.getRol());
    }

    public void cargarObject() {
        if (persona == null) {
            return;
        }
        if (hasMinLength(email, MIN_EMAIL_LENGTH)) {
            persona.setEmail(email.trim());
        }
        if (hasMinLength(direccion, MIN_DIRECCION_LENGTH)) {
            persona.setDireccion(direccion.trim());
        }
        if (hasMinLength(pais, MIN_PAIS_LENGTH)) {
            persona.setPais(pais.trim());
        }
        if (!isBlank(ciudad)) {
            persona.setCiudad(ciudad.trim());
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static boolean hasMinLength(String value, int minLength) {
        return !isBlank(value) && value.trim().length() >= minLength;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
