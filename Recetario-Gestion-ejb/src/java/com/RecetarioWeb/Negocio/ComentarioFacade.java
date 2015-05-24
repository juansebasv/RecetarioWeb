/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Negocio;

import com.RecetarioWeb.Entitys.Comentario;
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
public class ComentarioFacade extends AbstractFacade<Comentario> {

    @PersistenceContext(unitName = "Recetario-Gestion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioFacade() {
        super(Comentario.class);
    }

    public void registrarComentario(Comentario comentario) {
        comentario.setIdcomen(count());
        em.persist(comentario);
    }

    public void actualizarComentario(Comentario comentario) {
        em.merge(comentario);
    }

    public ArrayList<Comentario> findAll() {
        Query q = em.createNamedQuery("Comentario.findAll");
        try {
            return new ArrayList<Comentario>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Comentario findByIdReceta(String idrecetacomen) {
        Query q = em.createNamedQuery("Comentario.findByIdrecetacomen");
        q.setParameter("idrecetacomen", idrecetacomen);
        try {
            return (Comentario) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Comentario findByIdUser(String idusercomen) {
        Query q = em.createNamedQuery("Comentario.findByIdusercomen");
        q.setParameter("idusercomen", idusercomen);
        try {
            return (Comentario) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Comentario> findByFecha(String fechacomen) {
        Query q = em.createNamedQuery("Comentario.findByFechacomen");
        q.setParameter("fechacomen", fechacomen);
        try {
            return new ArrayList<Comentario>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }
}
