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
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByIdcat", query = "SELECT c FROM Categoria c WHERE c.idcat = :idcat"),
    @NamedQuery(name = "Categoria.findByNombrecat", query = "SELECT c FROM Categoria c WHERE c.nombrecat = :nombrecat"),
    @NamedQuery(name = "Categoria.findByFechacat", query = "SELECT c FROM Categoria c WHERE c.fechacat = :fechacat"),
    @NamedQuery(name = "Categoria.findByDescripcion", query = "SELECT c FROM Categoria c WHERE c.descripcion = :descripcion")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idcat")
    private int idcat;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrecat")
    private String nombrecat;
    @Column(name = "fechacat")
    @Temporal(TemporalType.DATE)
    private Date fechacat;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;

    public Categoria() {
    }

    public Categoria(String nombrecat) {
        this.nombrecat = nombrecat;
    }

    public Categoria(String nombrecat, int idcat) {
        this.nombrecat = nombrecat;
        this.idcat = idcat;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public String getNombrecat() {
        return nombrecat;
    }

    public void setNombrecat(String nombrecat) {
        this.nombrecat = nombrecat;
    }

    public Date getFechacat() {
        return fechacat;
    }

    public void setFechacat(Date fechacat) {
        this.fechacat = fechacat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombrecat != null ? nombrecat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.nombrecat == null && other.nombrecat != null) || (this.nombrecat != null && !this.nombrecat.equals(other.nombrecat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.RecetarioWeb.Entitys.Categoria[ nombrecat=" + nombrecat + " ]";
    }

}
