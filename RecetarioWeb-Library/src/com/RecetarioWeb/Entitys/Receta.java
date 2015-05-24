/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "receta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r"),
    @NamedQuery(name = "Receta.findByIdreceta", query = "SELECT r FROM Receta r WHERE r.idreceta = :idreceta"),
    @NamedQuery(name = "Receta.findByIduserreceta", query = "SELECT r FROM Receta r WHERE r.iduserreceta = :iduserreceta"),
    @NamedQuery(name = "Receta.findByIdcatreceta", query = "SELECT r FROM Receta r WHERE r.idcatreceta = :idcatreceta"),
    @NamedQuery(name = "Receta.findByNombrereceta", query = "SELECT r FROM Receta r WHERE r.nombrereceta = :nombrereceta"),
    @NamedQuery(name = "Receta.findByDescripcionreceta", query = "SELECT r FROM Receta r WHERE r.descripcionreceta = :descripcionreceta"),
    @NamedQuery(name = "Receta.findByAutorreceta", query = "SELECT r FROM Receta r WHERE r.autorreceta = :autorreceta"),
    @NamedQuery(name = "Receta.findByFechareceta", query = "SELECT r FROM Receta r WHERE r.fechareceta = :fechareceta"),
    @NamedQuery(name = "Receta.findByImagenreceta", query = "SELECT r FROM Receta r WHERE r.imagenreceta = :imagenreceta"),
    @NamedQuery(name = "Receta.findByIngredientes", query = "SELECT r FROM Receta r WHERE r.ingredientes = :ingredientes")})
public class Receta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idreceta")
    private int idreceta;
    @Size(max = 100)
    @Column(name = "iduserreceta")
    private String iduserreceta;
    @Column(name = "idcatreceta")
    private Integer idcatreceta;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrereceta")
    private String nombrereceta;
    @Size(max = 1000)
    @Column(name = "descripcionreceta")
    private String descripcionreceta;
    @Size(max = 100)
    @Column(name = "autorreceta")
    private String autorreceta;
    @Column(name = "fechareceta")
    @Temporal(TemporalType.DATE)
    private Date fechareceta;
    @Size(max = 1000)
    @Column(name = "imagenreceta")
    private String imagenreceta;
    @Size(max = 1000)
    @Column(name = "ingredientes")
    private String ingredientes;

    public Receta() {
    }

    public Receta(String nombrereceta) {
        this.nombrereceta = nombrereceta;
    }

    public Receta(String nombrereceta, int idreceta) {
        this.nombrereceta = nombrereceta;
        this.idreceta = idreceta;
    }

    public int getIdreceta() {
        return idreceta;
    }

    public void setIdreceta(int idreceta) {
        this.idreceta = idreceta;
    }

    public String getIduserreceta() {
        return iduserreceta;
    }

    public void setIduserreceta(String iduserreceta) {
        this.iduserreceta = iduserreceta;
    }

    public Integer getIdcatreceta() {
        return idcatreceta;
    }

    public void setIdcatreceta(Integer idcatreceta) {
        this.idcatreceta = idcatreceta;
    }

    public String getNombrereceta() {
        return nombrereceta;
    }

    public void setNombrereceta(String nombrereceta) {
        this.nombrereceta = nombrereceta;
    }

    public String getDescripcionreceta() {
        return descripcionreceta;
    }

    public void setDescripcionreceta(String descripcionreceta) {
        this.descripcionreceta = descripcionreceta;
    }

    public String getAutorreceta() {
        return autorreceta;
    }

    public void setAutorreceta(String autorreceta) {
        this.autorreceta = autorreceta;
    }

    public Date getFechareceta() {
        return fechareceta;
    }

    public void setFechareceta(Date fechareceta) {
        this.fechareceta = fechareceta;
    }

    public String getImagenreceta() {
        return imagenreceta;
    }

    public void setImagenreceta(String imagenreceta) {
        this.imagenreceta = imagenreceta;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombrereceta != null ? nombrereceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.nombrereceta == null && other.nombrereceta != null) || (this.nombrereceta != null && !this.nombrereceta.equals(other.nombrereceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.RecetarioWeb.Entitys.Receta[ nombrereceta=" + nombrereceta + " ]";
    }
    
}
