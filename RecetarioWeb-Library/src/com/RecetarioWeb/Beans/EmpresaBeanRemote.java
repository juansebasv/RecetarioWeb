/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Empresa;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Personal
 */
@Remote
public interface EmpresaBeanRemote {

    void registrarEmpresa(Empresa empresa);

    void actualizarEmpresa(Empresa empresa);

    ArrayList findAll();

    Empresa findByName(String name);
    
}
