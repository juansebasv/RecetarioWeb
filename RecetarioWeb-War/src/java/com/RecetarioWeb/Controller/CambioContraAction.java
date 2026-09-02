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
import com.RecetarioWeb.Negocio.PasswordHasher;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cambio de contrasena del usuario autenticado.
 *
 * @author Administrador
 */
public class CambioContraAction extends ActionSupport {

    private static final Logger LOG = Logger.getLogger(CambioContraAction.class.getName());

    private final PersonaBeanRemote personaBean = EjbLocator.lookup(PersonaBeanRemote.class);

    private final Client client = Client.getInstace();
    private Persona persona;
    private String contrasenaAnt;
    private String contrasenaNue;
    private String contrasenaConf;

    public CambioContraAction() {
        persona = personaBean.findByUsername(client.getNickname());
    }

    @Override
    public String execute() throws Exception {
        validar();
        Integer rol = persona == null ? null : persona.getRol();
        if (!hasErrors() && cargarObject()) {
            personaBean.actualizarPersona(persona);
            LOG.log(Level.INFO, "Contrasena actualizada para el usuario ''{0}''", client.getNickname());
            return Results.ok(rol);
        }
        return Results.ko(rol);
    }

    /**
     * Aplica el cambio: exige que la contrasena actual sea correcta y que la
     * nueva coincida con su confirmacion. La nueva clave se almacena con hash.
     */
    public boolean cargarObject() {
        if (persona == null || contrasenaNue == null || !contrasenaNue.equals(contrasenaConf)) {
            return false;
        }
        if (!PasswordHasher.matches(contrasenaAnt, persona.getPass())) {
            LOG.log(Level.WARNING, "Cambio de contrasena rechazado: clave actual incorrecta para ''{0}''",
                    client.getNickname());
            return false;
        }
        persona.setPass(PasswordHasher.hash(contrasenaNue));
        return true;
    }

    private void validar() {
        if (contrasenaAnt == null || contrasenaAnt.isEmpty()) {
            addFieldError("contrasenaAnt", "La contrasena actual es requerida");
        }
        if (contrasenaNue == null || contrasenaNue.isEmpty()) {
            addFieldError("contrasenaNue", "La contrasena nueva es requerida");
        }
        if (contrasenaConf == null || contrasenaConf.isEmpty()) {
            addFieldError("contrasenaConf", "La confirmacion es requerida");
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
}
