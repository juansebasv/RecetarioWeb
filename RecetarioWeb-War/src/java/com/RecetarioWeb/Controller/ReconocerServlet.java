/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Controller.support.WebKeys;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Guarda en el {@link Client} la seleccion del usuario (categoria a filtrar o
 * elemento a ver en detalle) y redirige a la vista correspondiente.
 *
 * @author Personal
 */
@WebServlet(name = "ReconocerServlet", urlPatterns = {"/ReconocerServlet"})
public class ReconocerServlet extends HttpServlet {

    /** Filtro de recetas por categoria: guarda la categoria elegida y redirige. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Client.getInstace().setNombre(request.getParameter("catredirec"));
        response.sendRedirect(WebKeys.VIEW_PAGE_RECETAS);
    }

    /**
     * "Leer mas": guarda el elemento seleccionado y abre su ficha de detalle.
     * El parametro {@code redirecTip} enruta a la ficha de tip; {@code redirec},
     * a la de receta.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Client client = Client.getInstace();
        String tip = request.getParameter("redirecTip");
        if (tip != null && !tip.isEmpty()) {
            client.setNombreTip(tip);
            response.sendRedirect(WebKeys.VIEW_PAGE_VIEW_TIP);
            return;
        }
        client.setNombre(request.getParameter("redirec"));
        response.sendRedirect(WebKeys.VIEW_PAGE_VIEW_RECETA);
    }

    @Override
    public String getServletInfo() {
        return "Enrutado de seleccion de catalogo de RecetarioWeb";
    }
}
