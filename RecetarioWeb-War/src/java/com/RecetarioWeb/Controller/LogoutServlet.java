package com.RecetarioWeb.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Controller.support.WebKeys;
import com.RecetarioWeb.Entitys.Persona;
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
 * Cierre de sesion.
 *
 * @author Personal
 */
@WebServlet(urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LogoutServlet.class.getName());

    @EJB
    private PersonaBeanRemote personaBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /** Cierra la sesion: marca la persona como inactiva e invalida cookie y sesion. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Client client = Client.getInstace();

        expireLoginCookie(request, response);

        String nickname = client.getNickname();
        if (nickname != null && !nickname.isEmpty()) {
            Persona cliente = personaBean.findByUsername(nickname);
            if (cliente != null) {
                cliente.setActivo(Boolean.FALSE);
                personaBean.actualizarPersona(cliente);
            }
            LOG.log(Level.INFO, "Logout de ''{0}''", nickname);
        }
        client.setNickname("");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // ?auth=logout -> la capa de UI muestra el mensaje de despedida.
        String despedida = (nickname == null || nickname.isEmpty())
                ? "?auth=logout"
                : "?auth=logout&u=" + encode(nickname);
        response.sendRedirect(WebKeys.VIEW_INDEX + despedida);
    }

    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private static void expireLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            if (WebKeys.NICKNAME.equals(cookie.getName())) {
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
                response.addCookie(cookie);
                return;
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Cierre de sesion de RecetarioWeb";
    }
}
