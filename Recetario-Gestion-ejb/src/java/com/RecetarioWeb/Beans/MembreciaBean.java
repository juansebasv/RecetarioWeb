/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Membrecia;
import com.RecetarioWeb.Negocio.MembreciaFacade;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Personal
 */
@Stateless
public class MembreciaBean implements MembreciaBeanRemote {

    @EJB
    private MembreciaFacade membreciaFacade;

    @Override
    public void registrarMembrecia(Membrecia membrecia) {
        membreciaFacade.registrarMembrecia(membrecia);
    }

    @Override
    public void actualizarMembrecia(Membrecia membrecia) {
        membreciaFacade.actualizarMembrecia(membrecia);
    }

    @Override
    public ArrayList findAll() {
        return membreciaFacade.findAll();
    }

    @Override
    public Membrecia findByUser(String idusername) {
        return membreciaFacade.findByUser(idusername);
    }

}
