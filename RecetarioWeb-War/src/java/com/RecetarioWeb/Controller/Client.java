/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

/**
 *
 * @author Personal
 */
public class Client {

    private static Client client = null;
    private String nombre = "";
    private String nombreTip = "";
    private String nickname = "";

    public Client() {
    }

    public static Client getInstace() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        Client.client = client;
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
