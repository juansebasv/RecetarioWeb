/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Tip;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Personal
 */
@Remote
public interface TipBeanRemote {

    void registrarTip(Tip tip);

    void actualizarTip(Tip tip);

    ArrayList findAll();

    Tip findByName(String nombretip);

    ArrayList findByFecha(String fecha);

    Tip findByIdUser(String idusertip);

}
