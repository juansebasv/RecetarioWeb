/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Beans;

import com.RecetarioWeb.Entitys.Empresa;
import com.RecetarioWeb.Negocio.EmpresaFacade;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Personal
 */
@Stateless
public class EmpresaBean implements EmpresaBeanRemote {

    @EJB
    private EmpresaFacade empresaFacade;

    @Override
    public void registrarEmpresa(Empresa empresa) {
        empresaFacade.registrarEmpresa(empresa);
    }

    @Override
    public void actualizarEmpresa(Empresa empresa) {
        empresaFacade.actualizarEmpresa(empresa);
    }

    @Override
    public ArrayList findAll() {
        return empresaFacade.findAll();
    }

    @Override
    public Empresa findByName(String name) {
        return empresaFacade.findByName(name);
    }

}
