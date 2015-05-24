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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c"),
    @NamedQuery(name = "Comentario.findByIdcomen", query = "SELECT c FROM Comentario c WHERE c.idcomen = :idcomen"),
    @NamedQuery(name = "Comentario.findByTextocomen", query = "SELECT c FROM Comentario c WHERE c.textocomen = :textocomen"),
    @NamedQuery(name = "Comentario.findByIdusercomen", query = "SELECT c FROM Comentario c WHERE c.idusercomen = :idusercomen"),
    @NamedQuery(name = "Comentario.findByIdrecetacomen", query = "SELECT c FROM Comentario c WHERE c.idrecetacomen = :idrecetacomen"),
    @NamedQuery(name = "Comentario.findByFechacomen", query = "SELECT c FROM Comentario c WHERE c.fechacomen = :fechacomen")})
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomen")
    private Integer idcomen;
    @Size(max = 2000)
    @Column(name = "textocomen")
    private String textocomen;
    @Size(max = 100)
    @Column(name = "idusercomen")
    private String idusercomen;
    @Column(name = "idrecetacomen")
    private Integer idrecetacomen;
    @Column(name = "fechacomen")
    @Temporal(TemporalType.DATE)
    private Date fechacomen;

    public Comentario() {
    }

    public Comentario(Integer idcomen) {
        this.idcomen = idcomen;
    }

    public Integer getIdcomen() {
        return idcomen;
    }

    public void setIdcomen(Integer idcomen) {
        this.idcomen = idcomen;
    }

    public String getTextocomen() {
        return textocomen;
    }

    public void setTextocomen(String textocomen) {
        this.textocomen = textocomen;
    }

    public String getIdusercomen() {
        return idusercomen;
    }

    public void setIdusercomen(String idusercomen) {
        this.idusercomen = idusercomen;
    }

    public Integer getIdrecetacomen() {
        return idrecetacomen;
    }

    public void setIdrecetacomen(Integer idrecetacomen) {
        this.idrecetacomen = idrecetacomen;
    }

    public Date getFechacomen() {
        return fechacomen;
    }

    public void setFechacomen(Date fechacomen) {
        this.fechacomen = fechacomen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomen != null ? idcomen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.idcomen == null && other.idcomen != null) || (this.idcomen != null && !this.idcomen.equals(other.idcomen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.RecetarioWeb.Entitys.Comentario[ idcomen=" + idcomen + " ]";
    }
    
}
