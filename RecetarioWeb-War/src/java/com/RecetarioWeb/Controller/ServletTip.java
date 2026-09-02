/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.ComentarioBeanRemote;
import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Beans.RecetaBeanRemote;
import com.RecetarioWeb.Beans.TipBeanRemote;
import com.RecetarioWeb.Controller.support.Roles;
import com.RecetarioWeb.Controller.support.WebKeys;
import com.RecetarioWeb.Entitys.Comentario;
import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Entitys.Receta;
import com.RecetarioWeb.Entitys.Tip;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Alta de tips (POST) y de comentarios sobre la receta seleccionada (GET).
 *
 * @author Personal
 */
@WebServlet(name = "ServletTip", urlPatterns = {"/ServletTip"})
public class ServletTip extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ServletTip.class.getName());

    @EJB
    private RecetaBeanRemote recetaBean;

    @EJB
    private ComentarioBeanRemote comentarioBean;

    @EJB
    private PersonaBeanRemote personaBean;

    @EJB
    private TipBeanRemote tipBean;

    /** Alta de un comentario sobre la receta seleccionada. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Client client = Client.getInstace();
        String texto = request.getParameter("descriComen");
        Persona persona = personaBean.findByUsername(client.getNickname());
        Receta receta = recetaBean.findByName(client.getNombre());

        if (persona != null && receta != null && texto != null && !texto.trim().isEmpty()) {
            Comentario comentario = new Comentario();
            comentario.setFechacomen(new Date());
            comentario.setIdrecetacomen(receta.getIdreceta());
            comentario.setTextocomen(texto);
            comentario.setIdusercomen(persona.getCodigo());
            comentarioBean.registrarComentario(comentario);
            LOG.log(Level.INFO, "Comentario de ''{0}'' en la receta ''{1}''",
                    new Object[]{persona.getCodigo(), receta.getNombrereceta()});
        }
        response.sendRedirect(WebKeys.VIEW_HOME_USER);
    }

    /** Alta de un tip por parte de un usuario autenticado. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Client client = Client.getInstace();
        String nameTip = request.getParameter("nameTip");
        String autorTip = request.getParameter("autorTip");
        String descripTip = request.getParameter("descripTip");
        Persona persona = personaBean.findByUsername(client.getNickname());

        if (persona == null) {
            response.sendRedirect(WebKeys.VIEW_INDEX);
            return;
        }
        boolean creado = false;
        if (nameTip != null && !nameTip.trim().isEmpty() && tipBean.findByName(nameTip.trim()) == null) {
            Tip tip = new Tip();
            tip.setAutortip(autorTip);
            tip.setDescripciontip(descripTip);
            tip.setNombretip(nameTip.trim());
            tip.setFechatip(new Date());
            tip.setIdusertip(persona.getCodigo());
            tipBean.registrarTip(tip);
            creado = true;
            LOG.log(Level.INFO, "Tip creado: ''{0}'' por ''{1}''",
                    new Object[]{nameTip.trim(), persona.getCodigo()});
        }
        String home = Roles.isAdmin(persona.getRol()) ? WebKeys.VIEW_HOME_ADMIN : WebKeys.VIEW_HOME_USER;
        response.sendRedirect(home + (creado ? "?created=tip" : ""));
    }

    @Override
    public String getServletInfo() {
        return "Alta de tips y comentarios de RecetarioWeb";
    }
}
