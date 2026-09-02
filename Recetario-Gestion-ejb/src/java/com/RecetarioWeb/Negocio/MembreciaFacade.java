/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Negocio;

import com.RecetarioWeb.Entitys.Membrecia;
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
public class MembreciaFacade extends AbstractFacade<Membrecia> {

    @PersistenceContext(unitName = "Recetario-Gestion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MembreciaFacade() {
        super(Membrecia.class);
    }

    public void registrarMembrecia(Membrecia membrecia) {
        membrecia.setIdmem(nextIdmem());
        em.persist(membrecia);
    }

    public void actualizarMembrecia(Membrecia membrecia) {
        em.merge(membrecia);
    }

    /** MAX(id)+1 para no reutilizar identificadores tras un borrado. */
    private int nextIdmem() {
        Number max = (Number) em.createQuery(
                "SELECT MAX(m.idmem) FROM Membrecia m").getSingleResult();
        return max == null ? 0 : max.intValue() + 1;
    }

    public ArrayList<Membrecia> findAll() {
        Query q = em.createNamedQuery("Membrecia.findAll");
        return new ArrayList<Membrecia>(q.getResultList());
    }

    public Membrecia findByUser(String idusermem) {
        Query q = em.createNamedQuery("Membrecia.findByIdusermem");
        q.setParameter("idusermem", idusermem);
        try {
            return (Membrecia) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
