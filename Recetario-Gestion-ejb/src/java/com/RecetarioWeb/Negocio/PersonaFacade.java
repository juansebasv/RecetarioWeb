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
        persona.setIdpersona(nextIdpersona());
        em.persist(persona);
    }

    public void actualizarPersona(Persona persona) {
        em.merge(persona);
    }

    /**
     * Siguiente valor libre para la columna no-clave {@code idpersona}. Se usa
     * MAX(id)+1 en lugar de COUNT(*) para no reutilizar identificadores tras un
     * borrado.
     */
    private int nextIdpersona() {
        Number max = (Number) em.createQuery(
                "SELECT MAX(p.idpersona) FROM Persona p").getSingleResult();
        return max == null ? 0 : max.intValue() + 1;
    }

    public ArrayList<Persona> findAll() {
        Query q = em.createNamedQuery("Persona.findAll");
        return new ArrayList<Persona>(q.getResultList());
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
        return new ArrayList<Persona>(q.getResultList());
    }

    public ArrayList<Persona> findByCiudad(String ciudad) {
        Query q = em.createNamedQuery("Persona.findByCiudad");
        q.setParameter("ciudad", ciudad);
        return new ArrayList<Persona>(q.getResultList());
    }

    public ArrayList<Persona> findByFecha(String fechanacimeinto) {
        Query q = em.createNamedQuery("Persona.findByFechanacimeinto");
        q.setParameter("fechanacimeinto", fechanacimeinto);
        return new ArrayList<Persona>(q.getResultList());
    }

    public ArrayList<Persona> findByRol(String rol) {
        Query q = em.createNamedQuery("Persona.findByRol");
        q.setParameter("rol", rol == null ? null : Integer.valueOf(rol.trim()));
        return new ArrayList<Persona>(q.getResultList());
    }

    public ArrayList<Persona> findByActivo(String activo) {
        Query q = em.createNamedQuery("Persona.findByActivo");
        q.setParameter("activo", Boolean.valueOf(activo));
        return new ArrayList<Persona>(q.getResultList());
    }
}
