/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Negocio;

import com.RecetarioWeb.Entitys.Empresa;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Personal
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "Recetario-Gestion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }

    public void registrarEmpresa(Empresa empresa) {
        empresa.setIdemp(count());
        em.persist(empresa);
    }

    public void actualizarEmpresa(Empresa empresa) {
        em.merge(empresa);
    }

    public ArrayList<Empresa> findAll() {
        Query q = em.createNamedQuery("Empresa.findAll");
        try {
            return new ArrayList<Empresa>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Empresa findByName(String name) {
        Query q = em.createNamedQuery("Empresa.findByNombreemp");
        q.setParameter("nombreemp", name);
        try {
            return (Empresa) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
