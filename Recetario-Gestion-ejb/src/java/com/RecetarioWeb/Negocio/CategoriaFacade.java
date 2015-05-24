/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Negocio;

import com.RecetarioWeb.Entitys.Categoria;
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
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "Recetario-Gestion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    public void registrarCategoria(Categoria categoria) {
        categoria.setIdcat(count());
        em.persist(categoria);
    }

    public void actualizarCategoria(Categoria categoria) {
        em.merge(categoria);
    }

    public ArrayList<Categoria> findAll() {
        Query q = em.createNamedQuery("Categoria.findAll");
        try {
            return new ArrayList<Categoria>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Categoria findByName(String name) {
        Query q = em.createNamedQuery("Categoria.findByNombrecat");
        q.setParameter("nombrecat", name);
        try {
            return (Categoria) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
