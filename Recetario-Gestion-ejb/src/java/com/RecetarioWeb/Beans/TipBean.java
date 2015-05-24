/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Tip;
import com.RecetarioWeb.Negocio.TipFacade;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Personal
 */
@Stateless
public class TipBean implements TipBeanRemote {

    @EJB
    private TipFacade tipFacade;

    @Override
    public void registrarTip(Tip tip) {
        tipFacade.registrarTip(tip);
    }

    @Override
    public void actualizarTip(Tip tip) {
        tipFacade.actualizarTip(tip);
    }

    @Override
    public ArrayList findAll() {
        return tipFacade.findAll();
    }

    @Override
    public Tip findByName(String nombretip) {
        return tipFacade.findByName(nombretip);
    }

    @Override
    public ArrayList findByFecha(String fecha) {
        return tipFacade.findByFecha(fecha);
    }

    @Override
    public Tip findByIdUser(String idusertip) {
        return tipFacade.findByIdUser(idusertip);
    }

}
