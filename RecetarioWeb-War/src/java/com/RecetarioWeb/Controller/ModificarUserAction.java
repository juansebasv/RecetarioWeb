/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Entitys.Persona;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Personal
 */
public class ModificarUserAction extends ActionSupport {

    PersonaBeanRemote personaBean = lookupPersonaBeanRemote();
    Client client = Client.getInstace();
    Persona persona;
    private String email;
    private String direccion;
    private String pais;
    private String ciudad;

    public ModificarUserAction() {
        persona = personaBean.findByUsername(client.getNickname());
    }

    public String execute() throws Exception {
        if (!hasErrors()) {
            cargarObject();
            personaBean.actualizarPersona(persona);
            if (persona.getRol().equals(1)) {
                return "success";
            } else {
                return "proccess";
            }
        } else {
            if (persona.getRol().equals(1)) {
                return "error";
            } else {
                return "failed";
            }
        }
    }

    public void cargarObject() {
        if (persona != null) {
            if (!email.equals("") && !email.equals(" ") && !email.isEmpty() && email.length() > 10) {
                persona.setEmail(email);
            }
            if (!direccion.equals("") && !direccion.equals(" ") && !direccion.isEmpty() && direccion.length() > 5) {
                persona.setDireccion(direccion);
            }
            if (!pais.equals("") && !pais.equals(" ") && !pais.isEmpty() && pais.length() >= 4) {
                persona.setPais(pais);
            }
            if (!ciudad.equals("") && !ciudad.equals(" ") && !ciudad.isEmpty()) {
                persona.setCiudad(ciudad);
            }
        }
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

    private PersonaBeanRemote lookupPersonaBeanRemote() {
        try {
            Context c = new InitialContext();
            return (PersonaBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/PersonaBean!com.RecetarioWeb.Beans.PersonaBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
