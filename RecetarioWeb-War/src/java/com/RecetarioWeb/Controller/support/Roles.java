/*
 * Roles de usuario. La columna persona.rol es un entero: 1 administrador,
 * 2 usuario estandar. Se centraliza aqui para no repetir los literales 1/2.
 */
package com.RecetarioWeb.Controller.support;

public final class Roles {

    public static final int ADMIN = 1;
    public static final int USUARIO = 2;

    private Roles() {
    }

    public static boolean isAdmin(Integer rol) {
        return rol != null && rol == ADMIN;
    }

    public static boolean isUsuario(Integer rol) {
        return rol != null && rol == USUARIO;
    }
}
