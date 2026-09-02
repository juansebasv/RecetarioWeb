/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

/**
 * Contexto de navegacion del usuario (nickname y elemento seleccionado en el
 * catalogo) compartido entre servlets, Struts Actions y JSP.
 *
 * <p><b>Limitacion conocida (deuda tecnica):</b> es un singleton de JVM, por lo
 * que el estado es global a todo el servidor y no por usuario. Es la pieza
 * historica del proyecto academico; migrarlo a {@code HttpSession} implica tocar
 * las ~20 JSP y queda fuera del alcance de esta iteracion.
 */
public final class Client {

    private static final Client INSTANCE = new Client();

    private String nombre = "";
    private String nombreTip = "";
    private String nickname = "";

    private Client() {
    }

    /** Nombre historico (con errata) conservado por compatibilidad con las JSP. */
    public static Client getInstace() {
        return INSTANCE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombreTip() {
        return nombreTip;
    }

    public void setNombreTip(String nombreTip) {
        this.nombreTip = nombreTip;
    }
}
