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
import com.RecetarioWeb.Entitys.Comentario;
import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Entitys.Receta;
import com.RecetarioWeb.Entitys.Tip;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Personal
 */
@WebServlet(name = "ServletTip", urlPatterns = {"/ServletTip"})
public class ServletTip extends HttpServlet {
    
    @EJB
    private RecetaBeanRemote recetaBean;
    
    @EJB
    private ComentarioBeanRemote comentarioBean;
    
    @EJB
    private PersonaBeanRemote personaBean;
    
    @EJB
    private TipBeanRemote tipBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletTip</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletTip at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        Client client = Client.getInstace();
        String descriComen = request.getParameter("descriComen");
        Persona persona = personaBean.findByUsername(client.getNickname());
        Receta receta = recetaBean.findByName(client.getNombre());
        if (persona != null) {
            Comentario comentario = new Comentario();
            comentario.setFechacomen(new Date());
            comentario.setIdrecetacomen(receta.getIdreceta());
            comentario.setTextocomen(descriComen);
            comentario.setIdusercomen(persona.getCodigo());
            comentarioBean.registrarComentario(comentario);
            response.sendRedirect("../RecetarioWeb-War/home_user.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        Client client = Client.getInstace();
        String nameTip = request.getParameter("nameTip");
        String autorTip = request.getParameter("autorTip");
        String descripTip = request.getParameter("descripTip");
        Persona persona = personaBean.findByUsername(client.getNickname());
        if (persona != null) {
            Tip tip = new Tip();
            tip.setAutortip(autorTip);
            tip.setDescripciontip(descripTip);
            tip.setNombretip(nameTip);
            tip.setFechatip(new Date());
            tip.setIdusertip(persona.getCodigo());
            tipBean.registrarTip(tip);
            if (persona.getRol().equals(1)) {
                response.sendRedirect("../RecetarioWeb-War/home_admin.jsp");
            } else if (persona.getRol().equals(2)) {
                response.sendRedirect("../RecetarioWeb-War/home_user.jsp");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
