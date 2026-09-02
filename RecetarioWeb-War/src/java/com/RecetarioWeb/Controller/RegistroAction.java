/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Controller.support.EjbLocator;
import com.RecetarioWeb.Controller.support.Results;
import com.RecetarioWeb.Controller.support.Roles;
import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Negocio.PasswordHasher;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Alta de un usuario estandar.
 *
 * @author Administrador
 */
public class RegistroAction extends ActionSupport {

    private static final Logger LOG = Logger.getLogger(RegistroAction.class.getName());
    /** Fecha de nacimiento esperada en formato ISO aaaa-mm-dd. */
    private static final String ISO_DATE_PATTERN = "\\d{4}-\\d{1,2}-\\d{1,2}";
    private static final String DATE_SEPARATOR = "-";

    private final PersonaBeanRemote personaBean = EjbLocator.lookup(PersonaBeanRemote.class);

    private Persona persona;
    private String name;
    private String codigo;
    private String username;
    private String pass;
    private String fecha;
    private String email;
    private String direccion;
    private String pais;
    private String ciudad;

    @Override
    public String execute() throws Exception {
        validar();
        if (hasErrors()) {
            return Results.ERROR;
        }
        cargarObject();
        LOG.log(Level.INFO, "Usuario registrado: codigo={0}, username={1}",
                new Object[]{codigo, username});
        return Results.SUCCESS;
    }

    public void cargarObject() {
        persona = new Persona();
        persona.setNombre(name);
        persona.setCodigo(codigo);
        persona.setUsername(username);
        persona.setPass(PasswordHasher.hash(pass));
        persona.setPais(pais);
        persona.setCiudad(ciudad);
        persona.setEmail(email);
        persona.setDireccion(direccion);
        persona.setFechanacimeinto(parseFecha(fecha));
        persona.setRol(Roles.USUARIO);
        persona.setActivo(Boolean.FALSE);
        personaBean.registrarPersona(persona);
    }

    /** Convierte una fecha ISO {@code aaaa-mm-dd} a {@link java.util.Date}. */
    private java.util.Date parseFecha(String iso) {
        String[] parts = iso.split(DATE_SEPARATOR);
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]));
        return cal.getTime();
    }

    private void validar() {
        if (name == null || name.isEmpty()) {
            addFieldError("name", "El nombre es requerido");
        }
        if (codigo == null || codigo.isEmpty()) {
            addFieldError("codigo", "El codigo es requerido");
        } else if (personaBean.findByCodigo(codigo) != null) {
            LOG.log(Level.WARNING, "Registro rechazado: codigo duplicado ''{0}''", codigo);
            addFieldError("codigo", "Ya existe un usuario con ese codigo");
        }
        if (username == null || username.isEmpty()) {
            addFieldError("username", "El username es requerido");
        } else if (personaBean.findByUsername(username) != null) {
            LOG.log(Level.WARNING, "Registro rechazado: username duplicado ''{0}''", username);
            addFieldError("username", "Ya existe un usuario con ese username");
        }
        if (pass == null || pass.isEmpty()) {
            addFieldError("pass", "El password es requerido");
        }
        if (email == null || email.isEmpty()) {
            addFieldError("email", "El email es requerido");
        }
        if (direccion == null || direccion.isEmpty()) {
            addFieldError("direccion", "La direccion es requerida");
        }
        if (pais == null || pais.isEmpty()) {
            addFieldError("pais", "El pais es requerido");
        }
        if (ciudad == null || ciudad.isEmpty()) {
            addFieldError("ciudad", "La ciudad es requerida");
        }
        if (fecha == null || fecha.isEmpty()) {
            addFieldError("fecha", "La fecha es requerida");
        } else if (!fecha.matches(ISO_DATE_PATTERN)) {
            addFieldError("fecha", "La fecha debe tener el formato aaaa-mm-dd");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
