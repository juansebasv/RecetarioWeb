/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.MembreciaBeanRemote;
import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Beans.RecetaBeanRemote;
import com.RecetarioWeb.Controller.support.AppConfig;
import com.RecetarioWeb.Controller.support.EjbLocator;
import com.RecetarioWeb.Controller.support.Results;
import com.RecetarioWeb.Entitys.Membrecia;
import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Entitys.Receta;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 * Alta de una receta. Publicar otorga puntos de membresia y, al superar el
 * umbral configurado de recetas, activa la membresia del autor.
 *
 * @author Administrador
 */
public class RecetaAction extends ActionSupport {

    private static final Logger LOG = Logger.getLogger(RecetaAction.class.getName());
    private static final String DIGITS_PATTERN = "\\d+";

    private final MembreciaBeanRemote membreciaBean = EjbLocator.lookup(MembreciaBeanRemote.class);
    private final RecetaBeanRemote recetaBean = EjbLocator.lookup(RecetaBeanRemote.class);
    private final PersonaBeanRemote personaBean = EjbLocator.lookup(PersonaBeanRemote.class);

    private final Client client = Client.getInstace();
    private final Persona persona;
    private final Receta receta = new Receta();
    private String nameRec;
    private String autorRec;
    private String ingreRec;
    private String descripRec;
    private String imageRec;
    private String fechaRec;
    private String idCategoria;
    private Part fileUpload;

    public RecetaAction() {
        persona = personaBean.findByUsername(client.getNickname());
    }

    @Override
    public String execute() throws Exception {
        validar();
        Integer rol = persona == null ? null : persona.getRol();
        if (persona == null || hasErrors()) {
            return Results.ko(rol);
        }
        cargarObject();
        LOG.log(Level.INFO, "Receta publicada: ''{0}'' por ''{1}''",
                new Object[]{nameRec, persona.getCodigo()});
        return Results.ok(rol);
    }

    public void cargarObject() {
        receta.setIduserreceta(persona.getCodigo());
        receta.setNombrereceta(nameRec);
        receta.setDescripcionreceta(descripRec);
        receta.setFechareceta(new Date());
        receta.setIngredientes(ingreRec);
        receta.setAutorreceta(autorRec);
        receta.setIdcatreceta(Integer.parseInt(idCategoria));

        actualizarMembrecia();
        recetaBean.registrarReceta(receta);
    }

    /**
     * Suma {@code membership.points-per-recipe} puntos al autor; si aun no tiene
     * membresia y ya alcanzo {@code membership.recipes-threshold} recetas, se la
     * crea activa.
     */
    private void actualizarMembrecia() {
        Membrecia mem = membreciaBean.findByUser(persona.getCodigo());
        int pointsPerRecipe = AppConfig.membershipPointsPerRecipe();
        if (mem != null) {
            int puntos = mem.getPuntos() == null ? 0 : mem.getPuntos();
            mem.setPuntos(puntos + pointsPerRecipe);
            membreciaBean.actualizarMembrecia(mem);
            return;
        }
        if (recetasPublicadasPor(persona.getCodigo()) >= AppConfig.membershipRecipesThreshold()) {
            mem = new Membrecia();
            mem.setIdusermem(persona.getCodigo());
            mem.setActivamem(Boolean.TRUE);
            mem.setFechamem(new Date());
            mem.setPuntos(pointsPerRecipe);
            membreciaBean.registrarMembrecia(mem);
            LOG.log(Level.INFO, "Membresia activada para ''{0}''", persona.getCodigo());
        }
    }

    private int recetasPublicadasPor(String codigo) {
        int total = 0;
        ArrayList<Receta> todas = recetaBean.findAll();
        for (Receta r : todas) {
            if (codigo.equals(r.getIduserreceta())) {
                total++;
            }
        }
        return total;
    }

    private void validar() {
        if (nameRec == null || nameRec.isEmpty()) {
            addFieldError("nameRec", "El nombre es requerido");
        } else if (recetaBean.findByName(nameRec) != null) {
            addFieldError("nameRec", "Ya existe una receta con ese nombre");
        }
        if (autorRec == null || autorRec.isEmpty()) {
            addFieldError("autorRec", "El autor es requerido");
        }
        if (descripRec == null || descripRec.isEmpty()) {
            addFieldError("descripRec", "La descripcion es requerida");
        }
        if (idCategoria == null || !idCategoria.matches(DIGITS_PATTERN)) {
            addFieldError("idCategoria", "Debe seleccionar una categoria valida");
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
}
