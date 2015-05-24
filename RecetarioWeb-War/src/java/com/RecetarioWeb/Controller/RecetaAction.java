/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.MembreciaBeanRemote;
import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Beans.RecetaBeanRemote;
import com.RecetarioWeb.Entitys.Membrecia;
import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Entitys.Receta;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;

/**
 *
 * @author Administrador
 */
public class RecetaAction extends ActionSupport {

    MembreciaBeanRemote membreciaBean = lookupMembreciaBeanRemote();
    RecetaBeanRemote recetaBean = lookupRecetaBeanRemote();
    PersonaBeanRemote personaBean = lookupPersonaBeanRemote();

    Client client = Client.getInstace();
    Persona persona;
    Receta receta;
    String nameRec;
    String autorRec;
    String ingreRec;
    String descripRec;
    String imageRec;
    String fechaRec;
    String idCategoria;
    Part fileUpload;

    public RecetaAction() {
        receta = new Receta();
        persona = personaBean.findByUsername(client.getNickname());
    }

    @Override
    public String execute() throws Exception {
        validar();
        if (!hasErrors()) {
            cargarObject();
            if (client.getNickname().equals("admin")) {
                return "success";
            } else {
                return "proccess";
            }
        } else {
            if (client.getNickname().equals("admin")) {
                return "error";
            } else {
                return "failed";
            }
        }
    }

    public void cargarObject() {
        receta.setIduserreceta(persona.getCodigo());
        receta.setNombrereceta(nameRec);
        receta.setDescripcionreceta(descripRec);
        receta.setFechareceta(new Date());
        receta.setIngredientes(ingreRec);
        receta.setAutorreceta(autorRec);
        receta.setIdcatreceta(Integer.parseInt(idCategoria));
        Membrecia mem = new Membrecia();
        mem = membreciaBean.findByUser(persona.getCodigo());
        if (mem != null) {
            int points = mem.getPuntos();
            mem.setPuntos(points + 10);
        } else {
            ArrayList<Receta> all = recetaBean.findAll();
            int cont = 0;
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getIduserreceta().equals(persona.getCodigo())) {
                    cont++;
                }
            }
            if (cont >= 10) {
                mem.setActivamem(Boolean.TRUE);
                mem.setFechamem(new Date());
                mem.setPuntos(10);
                mem.setIdusermem(persona.getCodigo());
                membreciaBean.registrarMembrecia(mem);
            }
        }
        recetaBean.registrarReceta(receta);
    }

    private void validar() {
        if (nameRec == null || nameRec.isEmpty()) {
            addFieldError("nameRec", "El nombre es requerido");
        }
        if (autorRec == null || autorRec.isEmpty()) {
            addFieldError("autorRec", "El password es requerido");
        }
        if (descripRec == null || descripRec.isEmpty()) {
            addFieldError("descripRec", "El password es requerido");
        }
    }

    public String getNameRec() {
        return nameRec;
    }

    public void setNameRec(String nameRec) {
        this.nameRec = nameRec;
    }

    public String getIngreRec() {
        return ingreRec;
    }

    public void setIngreRec(String ingreRec) {
        this.ingreRec = ingreRec;
    }

    public String getDescripRec() {
        return descripRec;
    }

    public void setDescripRec(String descripRec) {
        this.descripRec = descripRec;
    }

    public String getImageRec() {
        return imageRec;
    }

    public void setImageRec(String imageRec) {
        this.imageRec = imageRec;
    }

    public String getFechaRec() {
        return fechaRec;
    }

    public void setFechaRec(String fechaRec) {
        this.fechaRec = fechaRec;
    }

    public String getAutorRec() {
        return autorRec;
    }

    public void setAutorRec(String autorRec) {
        this.autorRec = autorRec;
    }

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
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

    private RecetaBeanRemote lookupRecetaBeanRemote() {
        try {
            Context c = new InitialContext();
            return (RecetaBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/RecetaBean!com.RecetarioWeb.Beans.RecetaBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MembreciaBeanRemote lookupMembreciaBeanRemote() {
        try {
            Context c = new InitialContext();
            return (MembreciaBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/MembreciaBean!com.RecetarioWeb.Beans.MembreciaBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
