/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Negocio;

import com.RecetarioWeb.Entitys.Persona;
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
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "Recetario-Gestion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public void registrarPersona(Persona persona) {
        persona.setIdpersona(count());
        em.persist(persona);
    }

    public void actualizarPersona(Persona persona) {
        em.merge(persona);
    }

    public ArrayList<Persona> findAll() {
        Query q = em.createNamedQuery("Persona.findAll");
        try {
            return new ArrayList<Persona>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Persona findByCodigo(String code) {
        Query q = em.createNamedQuery("Persona.findByCodigo");
        q.setParameter("codigo", code);
        try {
            return (Persona) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Persona findByName(String name) {
        Query q = em.createNamedQuery("Persona.findByNombre");
        q.setParameter("nombre", name);
        try {
            return (Persona) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Persona findByUsername(String username) {
        Query q = em.createNamedQuery("Persona.findByUsername");
        q.setParameter("username", username);
        try {
            return (Persona) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Persona> findByPais(String pais) {
        Query q = em.createNamedQuery("Persona.findByPais");
        q.setParameter("pais", pais);
        try {
            return new ArrayList<Persona>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Persona> findByCiudad(String ciudad) {
        Query q = em.createNamedQuery("Persona.findByCiudad");
        q.setParameter("ciudad", ciudad);
        try {
            return new ArrayList<Persona>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Persona> findByFecha(String fechanacimeinto) {
        Query q = em.createNamedQuery("Persona.findByFechanacimeinto");
        q.setParameter("fechanacimeinto", fechanacimeinto);
        try {
            return new ArrayList<Persona>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Persona> findByRol(String rol) {
        Query q = em.createNamedQuery("Persona.findByRol");
        q.setParameter("rol", rol);
        try {
            return new ArrayList<Persona>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Persona> findByActivo(String activo) {
        Query q = em.createNamedQuery("Persona.findByActivo");
        q.setParameter("activo", activo);
        try {
            return new ArrayList<Persona>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }
}
