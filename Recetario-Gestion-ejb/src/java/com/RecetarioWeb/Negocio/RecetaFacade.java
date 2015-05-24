/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Negocio;

import com.RecetarioWeb.Entitys.Receta;
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
public class RecetaFacade extends AbstractFacade<Receta> {

    @PersistenceContext(unitName = "Recetario-Gestion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetaFacade() {
        super(Receta.class);
    }

    public void registrarReceta(Receta receta) {
        receta.setIdreceta(count());
        em.persist(receta);
    }

    public void actualizarReceta(Receta receta) {
        em.merge(receta);
    }

    public ArrayList<Receta> findAll() {
        Query q = em.createNamedQuery("Receta.findAll");
        try {
            return new ArrayList<Receta>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Receta findByName(String nombrereceta) {
        Query q = em.createNamedQuery("Receta.findByNombrereceta");
        q.setParameter("nombrereceta", nombrereceta);
        try {
            return (Receta) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Receta> findByFecha(String fechareceta) {
        Query q = em.createNamedQuery("Receta.findByFechareceta");
        q.setParameter("fechareceta", fechareceta);
        try {
            return new ArrayList<Receta>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Receta findByIdUser(String iduserreceta) {
        Query q = em.createNamedQuery("Receta.findByIduserreceta");
        q.setParameter("iduserreceta", iduserreceta);
        try {
            return (Receta) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Receta findByIngredientes(String ingredientes) {
        Query q = em.createNamedQuery("Receta.findByIngredientes");
        q.setParameter("ingredientes", ingredientes);
        try {
            return (Receta) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public Receta findByIdCategoria(int idcatreceta) {
        Query q = em.createNamedQuery("Receta.findByIdcatreceta");
        q.setParameter("idcatreceta", idcatreceta);
        try {
            return (Receta) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
