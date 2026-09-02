/*
 * Nombres de resultado de las Struts Actions. Deben coincidir EXACTAMENTE con
 * los <result> declarados en struts.xml (incluido el historico "proccess").
 */
package com.RecetarioWeb.Controller.support;

public final class Results {

    public static final String SUCCESS = "success";
    /** Flujo de usuario estandar. Ojo: el nombre en struts.xml lleva la errata "proccess". */
    public static final String PROCESS = "proccess";
    public static final String ERROR = "error";
    /** Flujo de usuario estandar cuando algo falla. */
    public static final String FAILED = "failed";

    private Results() {
    }

    /** Resultado feliz segun el rol: admin -> SUCCESS, usuario -> PROCESS. */
    public static String ok(Integer rol) {
        return Roles.isAdmin(rol) ? SUCCESS : PROCESS;
    }

    /** Resultado de fallo segun el rol: admin -> ERROR, usuario -> FAILED. */
    public static String ko(Integer rol) {
        return Roles.isAdmin(rol) ? ERROR : FAILED;
    }
}
