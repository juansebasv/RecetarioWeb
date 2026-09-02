/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.CetagoriaBeanRemote;
import com.RecetarioWeb.Controller.support.EjbLocator;
import com.RecetarioWeb.Controller.support.Results;
import com.RecetarioWeb.Entitys.Categoria;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Alta de una categoria (solo administrador).
 *
 * @author Administrador
 */
public class CategoriaAction extends ActionSupport {

    private static final Logger LOG = Logger.getLogger(CategoriaAction.class.getName());

    private final CetagoriaBeanRemote cetagoriaBean = EjbLocator.lookup(CetagoriaBeanRemote.class);

    private final Categoria categoria = new Categoria();
    private String nameCat;
    private String fechaCat;
    private String desCat;

    @Override
    public String execute() throws Exception {
        validar();
        if (hasErrors()) {
            return Results.ERROR;
        }
        cargarObject();
        LOG.log(Level.INFO, "Categoria creada: ''{0}''", nameCat);
        return Results.SUCCESS;
    }

    public void cargarObject() {
        categoria.setNombrecat(nameCat);
        categoria.setFechacat(new Date());
        categoria.setDescripcion(desCat);
        cetagoriaBean.registrarCategoria(categoria);
    }

    private void validar() {
        if (nameCat == null || nameCat.isEmpty()) {
            addFieldError("nameCat", "El nombre es requerido");
        } else if (cetagoriaBean.findByName(nameCat) != null) {
            LOG.log(Level.WARNING, "Alta de categoria rechazada: nombre duplicado ''{0}''", nameCat);
            addFieldError("nameCat", "Ya existe una categoria con ese nombre");
        }
        if (desCat == null || desCat.isEmpty()) {
            addFieldError("desCat", "La descripcion es requerida");
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
}
