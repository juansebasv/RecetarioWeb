/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.CetagoriaBeanRemote;
import com.RecetarioWeb.Beans.ComentarioBeanRemote;
import com.RecetarioWeb.Beans.EmpresaBeanRemote;
import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Beans.RecetaBeanRemote;
import com.RecetarioWeb.Beans.TipBeanRemote;
import com.RecetarioWeb.Entitys.Categoria;
import com.RecetarioWeb.Entitys.Comentario;
import com.RecetarioWeb.Entitys.Empresa;
import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Entitys.Receta;
import com.RecetarioWeb.Entitys.Tip;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Personal
 */
public class ControllerReceta {

    ComentarioBeanRemote comentarioBean = lookupComentarioBeanRemote();
    CetagoriaBeanRemote cetagoriaBean = lookupCetagoriaBeanRemote();
    PersonaBeanRemote personaBean = lookupPersonaBeanRemote();
    EmpresaBeanRemote empresaBean = lookupEmpresaBeanRemote();
    TipBeanRemote tipBean = lookupTipBeanRemote();
    RecetaBeanRemote recetaBean = lookupRecetaBeanRemote();

    private ArrayList<Receta> recetas = new ArrayList();
    private ArrayList<Tip> tips = new ArrayList();
    private ArrayList<Empresa> empresas = new ArrayList();
    private ArrayList<Persona> usuarios = new ArrayList();
    private ArrayList<Categoria> categorias = new ArrayList();
    private ArrayList<Comentario> comentario = new ArrayList();

    private Receta receta_1 = new Receta();
    private Receta receta_2 = new Receta();
    private Receta receta = new Receta();
    private Tip tip = new Tip();

    public ControllerReceta() {
    }

    public void cargarRecetas(String nombre) {
        System.out.println(":::::: " + nombre);
        Categoria categoria = cetagoriaBean.findByName(nombre);
        if (categoria == null || nombre == null || nombre.isEmpty()) {
            recetas = recetaBean.findAll();
        } else {
            ArrayList<Receta> aux = recetaBean.findAll();
            System.out.println("entro: " + aux.size());
            for (int i = 0; i < aux.size(); i++) {
                System.out.println("id:   " + aux.get(i).getIdcatreceta());
                if (aux.get(i).getIdcatreceta() != null) {
                    int y = aux.get(i).getIdcatreceta();
                    int p = categoria.getIdcat();
                    String num1 = Integer.toString(p);
                    String num2 = Integer.toString(y);
                    System.out.println(":::::: " + num1 + " " + num2);
                    if (num1.equals(num2)) {
                        System.out.println("entro2");
                        recetas.add(aux.get(i));
                    }
                }
            }
        }
    }

    public void cargarTips() {
        tips = tipBean.findAll();
    }

    public void cargarEmpresas() {
        empresas = empresaBean.findAll();
    }

    public void cargarUsuarios() {
        usuarios = personaBean.findAll();
    }

    public void cargarDuo() {
        ArrayList<Receta> aux = new ArrayList();
        aux = recetaBean.findAll();
        if (aux.size() != 0 && aux.size() > 2) {
            receta_1 = aux.get(aux.size() - 1);
            receta_2 = aux.get(aux.size() - 2);
        }
    }

    public void reconocerReceta(String nombre) {
        ArrayList<Receta> aux = new ArrayList();
        aux = recetaBean.findAll();
        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getNombrereceta().equals(nombre)) {
                receta = aux.get(i);
                break;
            }
        }
    }

    public void reconocerTip(String nombre) {
        ArrayList<Tip> aux = new ArrayList();
        aux = tipBean.findAll();
        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getNombretip().equals(nombre)) {
                tip = aux.get(i);
                break;
            }
        }
    }

    public String formatText(String nombre) {
        String array[] = nombre.split(" ");
        String ans = "";
        if (array.length > 20) {
            for (int i = 0; i < array.length; i++) {
                if (i > 20) {
                    break;
                } else {
                    if (i % 5 == 0 && i != 0) {
                        ans += array[i] + "<br/>";
                    } else {
                        ans += array[i] + " ";
                    }
                }
            }
            ans += "...";
            return ans;
        } else {
            return nombre;
        }
    }

    public String formatIngre(String nombre) {
        String array[] = nombre.split("-");
        String ans = "";
        for (int i = 0; i < array.length; i++) {
            ans += array[i] + "<br/>";
        }
        return ans;
    }

    public String formatDescri(String nombre) {
        String array[] = nombre.split("-");
        String ans = "";
        for (int i = 0; i < array.length; i++) {
            ans += array[i] + "<br/>";
        }
        return ans;
    }

    public void cargarComent(String nombre) {
        ArrayList<Comentario> aux = comentarioBean.findAll();
        Receta receta = recetaBean.findByName(nombre);
        System.out.println("nombre " + nombre + " " + receta.getNombrereceta());
        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getIdrecetacomen() == receta.getIdreceta()) {
                System.out.println("entro");
                comentario.add(aux.get(i));
            }
        }
    }

    public String nombreUser(String nombre) {
        Persona persona = personaBean.findByCodigo(nombre);
        return persona.getNombre();
    }

    public ArrayList<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(ArrayList<Comentario> comentario) {
        this.comentario = comentario;
    }

    public void categorias() {
        categorias = cetagoriaBean.findAll();
    }

    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(ArrayList<Receta> recetas) {
        this.recetas = recetas;
    }

    public ArrayList<Tip> getTips() {
        return tips;
    }

    public void setTips(ArrayList<Tip> tips) {
        this.tips = tips;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public ArrayList<Persona> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Persona> usuarios) {
        this.usuarios = usuarios;
    }

    public Receta getReceta_1() {
        return receta_1;
    }

    public void setReceta_1(Receta receta_1) {
        this.receta_1 = receta_1;
    }

    public Receta getReceta_2() {
        return receta_2;
    }

    public void setReceta_2(Receta receta_2) {
        this.receta_2 = receta_2;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    private RecetaBeanRemote lookupRecetaBeanRemote() {
        try {
            Context c = new InitialContext();
            return (RecetaBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/RecetaBean!com.RecetarioWeb.Beans.RecetaBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private TipBeanRemote lookupTipBeanRemote() {
        try {
            Context c = new InitialContext();
            return (TipBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/TipBean!com.RecetarioWeb.Beans.TipBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EmpresaBeanRemote lookupEmpresaBeanRemote() {
        try {
            Context c = new InitialContext();
            return (EmpresaBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/EmpresaBean!com.RecetarioWeb.Beans.EmpresaBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PersonaBeanRemote lookupPersonaBeanRemote() {
        try {
            Context c = new InitialContext();
            return (PersonaBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/PersonaBean!com.RecetarioWeb.Beans.PersonaBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private CetagoriaBeanRemote lookupCetagoriaBeanRemote() {
        try {
            Context c = new InitialContext();
            return (CetagoriaBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/CetagoriaBean!com.RecetarioWeb.Beans.CetagoriaBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ComentarioBeanRemote lookupComentarioBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ComentarioBeanRemote) c.lookup("java:global/Recetario-Gestion-ejb/ComentarioBean!com.RecetarioWeb.Beans.ComentarioBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
