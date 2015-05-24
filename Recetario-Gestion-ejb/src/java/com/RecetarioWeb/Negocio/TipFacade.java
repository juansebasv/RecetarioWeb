/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Negocio;

import com.RecetarioWeb.Entitys.Tip;
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
public class TipFacade extends AbstractFacade<Tip> {

    @PersistenceContext(unitName = "Recetario-Gestion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipFacade() {
        super(Tip.class);
    }

    public void registrarTip(Tip tip) {
        tip.setIdtip(count());
        em.persist(tip);
    }

    public void actualizarTip(Tip tip) {
        em.merge(tip);
    }

    public ArrayList<Tip> findAll() {
        Query q = em.createNamedQuery("Tip.findAll");
        try {
            return new ArrayList<Tip>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Tip findByName(String nombretip) {
        Query q = em.createNamedQuery("Tip.findByNombretip");
        q.setParameter("nombretip", nombretip);
        try {
            return (Tip) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Tip> findByFecha(String fechatip) {
        Query q = em.createNamedQuery("Tip.findByFechatip");
        q.setParameter("fechatip", fechatip);
        try {
            return new ArrayList<Tip>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Tip findByIdUser(String idusertip) {
        Query q = em.createNamedQuery("Tip.findByIdusertip");
        q.setParameter("idusertip", idusertip);
        try {
            return (Tip) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
