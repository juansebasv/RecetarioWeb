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
 * @author Administrador
 */
public class CambioContraAction extends ActionSupport {

    PersonaBeanRemote personaBean = lookupPersonaBeanRemote();

    Client client = Client.getInstace();
    Persona persona;
    String contrasenaAnt;
    String contrasenaNue;
    String contrasenaConf;

    public CambioContraAction() {
        persona = personaBean.findByUsername(client.getNickname());
    }

    @Override
    public String execute() throws Exception {
        validar();
        if (!hasErrors()) {
            if (cargarObject()) {
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
        } else {
            if (persona.getRol().equals(1)) {
                return "error";
            } else {
                return "failed";
            }
        }
    }

    public boolean cargarObject() {
        if (persona != null) {
            if (contrasenaNue.equals(contrasenaConf)) {
                persona.setPass(contrasenaConf);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void validar() {
        if (contrasenaAnt == null || contrasenaAnt.isEmpty()) {
            addFieldError("contrasenaAnt", "La contraseña es requerida");
        }
        if (contrasenaNue == null || contrasenaNue.isEmpty()) {
            addFieldError("contrasenaNue", "La contraseña es requerido");
        }
        if (contrasenaConf == null || contrasenaConf.isEmpty()) {
            addFieldError("contrasenaConf", "La contraseña es requerido");
        }
    }

    public String getContrasenaAnt() {
        return contrasenaAnt;
    }

    public void setContrasenaAnt(String contrasenaAnt) {
        this.contrasenaAnt = contrasenaAnt;
    }

    public String getContrasenaNue() {
        return contrasenaNue;
    }

    public void setContrasenaNue(String contrasenaNue) {
        this.contrasenaNue = contrasenaNue;
    }

    public String getContrasenaConf() {
        return contrasenaConf;
    }

    public void setContrasenaConf(String contrasenaConf) {
        this.contrasenaConf = contrasenaConf;
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
