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
import com.RecetarioWeb.Controller.support.AppConfig;
import com.RecetarioWeb.Controller.support.EjbLocator;
import com.RecetarioWeb.Entitys.Categoria;
import com.RecetarioWeb.Entitys.Comentario;
import com.RecetarioWeb.Entitys.Empresa;
import com.RecetarioWeb.Entitys.Persona;
import com.RecetarioWeb.Entitys.Receta;
import com.RecetarioWeb.Entitys.Tip;
import java.util.ArrayList;

/**
 * Fachada de solo lectura que consumen las JSP (via scriptlet) para pintar
 * catalogos y fichas. Resuelve los EJB a traves de {@link EjbLocator}.
 *
 * @author Personal
 */
public class ControllerReceta {

    private static final String LINE_BREAK = "<br/>";
    private static final String INGREDIENT_SEPARATOR = "-";

    private final ComentarioBeanRemote comentarioBean = EjbLocator.lookup(ComentarioBeanRemote.class);
    private final CetagoriaBeanRemote cetagoriaBean = EjbLocator.lookup(CetagoriaBeanRemote.class);
    private final PersonaBeanRemote personaBean = EjbLocator.lookup(PersonaBeanRemote.class);
    private final EmpresaBeanRemote empresaBean = EjbLocator.lookup(EmpresaBeanRemote.class);
    private final TipBeanRemote tipBean = EjbLocator.lookup(TipBeanRemote.class);
    private final RecetaBeanRemote recetaBean = EjbLocator.lookup(RecetaBeanRemote.class);

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
        ArrayList<Receta> todas = recetaBean.findAll();
        if (todas == null) {
            todas = new ArrayList<Receta>();
        }
        Categoria categoria = (nombre == null || nombre.isEmpty()) ? null : cetagoriaBean.findByName(nombre);
        if (categoria == null) {
            recetas = todas;
            return;
        }
        recetas = new ArrayList<Receta>();
        for (Receta r : todas) {
            if (r.getIdcatreceta() != null && r.getIdcatreceta() == categoria.getIdcat()) {
                recetas.add(r);
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
        ArrayList<Receta> aux = recetaBean.findAll();
        if (aux != null && aux.size() >= 2) {
            receta_1 = aux.get(aux.size() - 1);
            receta_2 = aux.get(aux.size() - 2);
        }
    }

    public void reconocerReceta(String nombre) {
        if (nombre == null) {
            return;
        }
        ArrayList<Receta> aux = recetaBean.findAll();
        if (aux == null) {
            return;
        }
        for (Receta r : aux) {
            if (nombre.equals(r.getNombrereceta())) {
                receta = r;
                break;
            }
        }
    }

    public void reconocerTip(String nombre) {
        if (nombre == null) {
            return;
        }
        ArrayList<Tip> aux = tipBean.findAll();
        if (aux == null) {
            return;
        }
        for (Tip t : aux) {
            if (nombre.equals(t.getNombretip())) {
                tip = t;
                break;
            }
        }
    }

    /**
     * Recorta un texto largo para la portada: a partir de
     * {@code text.summary.max-words} palabras lo corta e inserta un salto cada
     * {@code text.summary.words-per-line}. Siempre devuelve HTML escapado.
     */
    public String formatText(String texto) {
        if (texto == null) {
            return "";
        }
        int maxWords = AppConfig.summaryMaxWords();
        int wordsPerLine = AppConfig.summaryWordsPerLine();
        String[] palabras = texto.split(" ");
        if (palabras.length <= maxWords) {
            return escape(texto);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= maxWords && i < palabras.length; i++) {
            sb.append(escape(palabras[i]));
            sb.append(i != 0 && i % wordsPerLine == 0 ? LINE_BREAK : " ");
        }
        return sb.append("...").toString();
    }

    public String formatIngre(String texto) {
        return joinLines(texto);
    }

    public String formatDescri(String texto) {
        return joinLines(texto);
    }

    /** Divide por '-' y une con saltos de linea, escapando cada fragmento. */
    private String joinLines(String texto) {
        if (texto == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String parte : texto.split(INGREDIENT_SEPARATOR)) {
            sb.append(escape(parte)).append(LINE_BREAK);
        }
        return sb.toString();
    }

    public void cargarComent(String nombre) {
        Receta actual = (nombre == null) ? null : recetaBean.findByName(nombre);
        ArrayList<Comentario> aux = comentarioBean.findAll();
        if (actual == null || aux == null) {
            return;
        }
        for (Comentario c : aux) {
            if (c.getIdrecetacomen() != null && c.getIdrecetacomen() == actual.getIdreceta()) {
                comentario.add(c);
            }
        }
    }

    public String nombreUser(String codigo) {
        if (codigo == null) {
            return "";
        }
        Persona persona = personaBean.findByCodigo(codigo);
        return escape(persona == null ? codigo : persona.getNombre());
    }

    /** Escapa caracteres HTML para evitar XSS almacenado al pintar en las JSP. */
    public static String escape(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(value.length() + 16);
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '&': sb.append("&amp;"); break;
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                case '"': sb.append("&quot;"); break;
                case '\'': sb.append("&#39;"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
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

}
