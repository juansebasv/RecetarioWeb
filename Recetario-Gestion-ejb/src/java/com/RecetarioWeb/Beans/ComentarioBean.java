/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Comentario;
import com.RecetarioWeb.Negocio.ComentarioFacade;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Personal
 */
@Stateless
public class ComentarioBean implements ComentarioBeanRemote {
    
    @EJB
    private ComentarioFacade comentarioFacade;
    
    @Override
    public void registrarComentario(Comentario comentario) {
        comentarioFacade.registrarComentario(comentario);
    }
    
    @Override
    public void actualizarComentario(Comentario comentario) {
        comentarioFacade.actualizarComentario(comentario);
    }
    
    @Override
    public ArrayList findAll() {
        return comentarioFacade.findAll();
    }
    
    @Override
    public Comentario findByIdReceta(String comentario) {
        return comentarioFacade.findByIdReceta(comentario);
    }
    
    @Override
    public Comentario findByIdUser(String idusercomen) {
        return comentarioFacade.findByIdUser(idusercomen);
    }
    
    @Override
    public ArrayList findByFecha(String fecha) {
        return comentarioFacade.findByFecha(fecha);
    }
    
}