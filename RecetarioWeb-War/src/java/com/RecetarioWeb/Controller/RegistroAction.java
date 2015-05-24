/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Entitys.Persona;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Administrador
 */
public class RegistroAction extends ActionSupport {

    PersonaBeanRemote personaBean = lookupPersonaBeanRemote();

    Persona persona;
    private String name;
    private String codigo;
    private String username;
    private String pass;
    private String fecha;
    private String email;
    private String direccion;
    private String pais;
    private String ciudad;

    public RegistroAction() {
    }

    @Override
    public String execute() throws Exception {
        validar();
        if (!hasErrors()) {
            cargarObject();
            return "success";
        } else {
            return "error";
        }
    }

    public void cargarObject() {
        persona = new Persona();
        persona.setNombre(name);
        persona.setCodigo(codigo);
        persona.setUsername(username);
        persona.setPass(pass);
        persona.setPais(pais);
        persona.setCiudad(ciudad);
        persona.setEmail(email);
        persona.setDireccion(direccion);
        String[] array = fecha.split("-");
        int anio = Integer.parseInt(array[0]);
        int mes = Integer.parseInt(array[1]);
        int dia = Integer.parseInt(array[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(anio, mes - 1, dia);
        java.util.Date nuevaFecha = cal.getTime();
        persona.setFechanacimeinto(nuevaFecha);
        persona.setRol(2);
        persona.setActivo(Boolean.FALSE);
        System.out.println("::::::::::::::::::::::: REGISTRO");
        personaBean.registrarPersona(persona);
    }

    private void validar() {
        if (name == null || name.isEmpty()) {
            addFieldError("name", "El nombre es requerido");
        }
        if (codigo == null || codigo.isEmpty()) {
            addFieldError("codigo", "El codigo es requerido");
        }
        if (username == null || username.isEmpty()) {
            addFieldError("username", "El username es requerido");
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
