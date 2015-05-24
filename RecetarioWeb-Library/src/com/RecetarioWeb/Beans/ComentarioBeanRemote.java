/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Comentario;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Personal
 */
@Remote
public interface ComentarioBeanRemote {

    void registrarComentario(Comentario comentario);

    void actualizarComentario(Comentario comentario);

    ArrayList findAll();

    Comentario findByIdReceta(String comentario);

    Comentario findByIdUser(String idusercomen);

    ArrayList findByFecha(String fecha);
    
}
