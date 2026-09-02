/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Controller.support.AppConfig;
import com.RecetarioWeb.Controller.support.Roles;
import com.RecetarioWeb.Controller.support.WebKeys;
import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Negocio.PasswordHasher;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Autenticacion de usuarios.
 *
 * @author Personal
 */
@WebServlet(name = "SesionController", urlPatterns = {"/SesionController"})
public class SesionController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SesionController.class.getName());

    @EJB
    private PersonaBeanRemote personaBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(WebKeys.VIEW_INDEX);
    }

    /**
     * Autentica al usuario: valida el hash de la contrasena, migra las claves
     * heredadas en texto plano al formato con hash y redirige segun el rol.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter(WebKeys.NICKNAME);
        String pass = request.getParameter("pass");

        Persona usuario = (name == null || name.isEmpty()) ? null : personaBean.findByUsername(name);
        if (usuario == null || pass == null || !PasswordHasher.matches(pass, usuario.getPass())) {
            LOG.log(Level.WARNING, "Intento de login fallido para usuario ''{0}''", name);
            response.sendRedirect(WebKeys.VIEW_INDEX + "?auth=loginfail");
            return;
        }

        if (PasswordHasher.needsRehash(usuario.getPass())) {
            usuario.setPass(PasswordHasher.hash(pass));
            LOG.log(Level.INFO, "Contrasena de ''{0}'' migrada al formato con hash", name);
        }
        usuario.setActivo(Boolean.TRUE);
        personaBean.actualizarPersona(usuario);

        HttpSession session = request.getSession();
        session.setAttribute(WebKeys.NICKNAME, name);
        Client.getInstace().setNickname(name);
        response.addCookie(buildLoginCookie(request, name));
        LOG.log(Level.INFO, "Login correcto: ''{0}'' (rol={1})", new Object[]{name, usuario.getRol()});

        // ?auth=login&u=<nick> -> la capa de UI muestra el saludo de bienvenida.
        response.sendRedirect(homeFor(usuario.getRol()) + "?auth=login&u=" + encode(name));
    }

    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private Cookie buildLoginCookie(HttpServletRequest request, String name) {
        Cookie cookie = new Cookie(WebKeys.NICKNAME, name);
        cookie.setMaxAge(AppConfig.sessionCookieMaxAgeSeconds());
        cookie.setHttpOnly(true);
        cookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
        return cookie;
    }

    private static String homeFor(Integer rol) {
        if (Roles.isAdmin(rol)) {
            return WebKeys.VIEW_HOME_ADMIN;
        }
        if (Roles.isUsuario(rol)) {
            return WebKeys.VIEW_HOME_USER;
        }
        return WebKeys.VIEW_INDEX;
    }

    @Override
    public String getServletInfo() {
        return "Autenticacion de usuarios de RecetarioWeb";
    }
}
