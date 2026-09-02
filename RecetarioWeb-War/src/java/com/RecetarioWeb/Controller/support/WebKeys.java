/*
 * Claves y destinos de navegacion usados por servlets y JSP. Evita repetir
 * literales de cookies/atributos de sesion y rutas de redireccion.
 */
package com.RecetarioWeb.Controller.support;

public final class WebKeys {

    /** Nombre de la cookie y del atributo de sesion con el usuario autenticado. */
    public static final String NICKNAME = "nickname";

    /** Vistas destino de las redirecciones de los servlets. */
    public static final String VIEW_INDEX = "index.jsp";
    public static final String VIEW_HOME_ADMIN = "home_admin.jsp";
    public static final String VIEW_HOME_USER = "home_user.jsp";
    public static final String VIEW_PAGE_RECETAS = "page_recetas.jsp";
    public static final String VIEW_PAGE_VIEW_RECETA = "page_view_receta.jsp";
    public static final String VIEW_PAGE_VIEW_TIP = "page_view_tip.jsp";
    public static final String VIEW_FORM_EMPRESA = "/form_empresa.jsp";

    private WebKeys() {
    }
}
