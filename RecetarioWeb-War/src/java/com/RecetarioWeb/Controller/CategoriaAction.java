/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.CetagoriaBeanRemote;
import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Entitys.Categoria;
import com.RecetarioWeb.Entitys.Persona;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Administrador
 */
public class CategoriaAction extends ActionSupport {

    PersonaBeanRemote personaBean = lookupPersonaBeanRemote();
    CetagoriaBeanRemote cetagoriaBean = lookupCetagoriaBeanRemote();

    Client client = Client.getInstace();
    Categoria categoria;
    Persona persona;
    String nameCat;
    String fechaCat;
    String desCat;

    public CategoriaAction() {
        categoria = new Categoria();
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
        categoria.setNombrecat(nameCat);
        categoria.setFechacat(new Date());
        categoria.setDescripcion(desCat);
        cetagoriaBean.registrarCategoria(categoria);
    }

    private void validar() {
        if (nameCat == null || nameCat.isEmpty()) {
            addFieldError("nameCat", "El nickname es requerido");
        }
        if (desCat == null || desCat.isEmpty()) {
            addFieldError("desCat", "La descripcion es requerido");
        }
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public String getFechaCat() {
        return fechaCat;
    }

    public void setFechaCat(String fechaCat) {
        this.fechaCat = fechaCat;
    }

    public String getDesCat() {
        return desCat;
    }

    public void setDesCat(String desCat) {
        this.desCat = desCat;
    }

    private CetagoriaBeanRemote lookupCetagoriaBeanRemote() {
        try {
            Context c = new InitialContext();
            return (CetagoriaBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/CetagoriaBean!com.RecetarioWeb.Beans.CetagoriaBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
