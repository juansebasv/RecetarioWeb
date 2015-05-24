/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Membrecia;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Personal
 */
@Remote
public interface MembreciaBeanRemote {

    void registrarMembrecia(Membrecia membrecia);

    void actualizarMembrecia(Membrecia membrecia);

    ArrayList findAll();

    Membrecia findByUser(String idusername);
    
}
