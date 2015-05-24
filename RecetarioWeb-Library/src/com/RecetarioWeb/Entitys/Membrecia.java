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
@Table(name = "membrecia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membrecia.findAll", query = "SELECT m FROM Membrecia m"),
    @NamedQuery(name = "Membrecia.findByIdmem", query = "SELECT m FROM Membrecia m WHERE m.idmem = :idmem"),
    @NamedQuery(name = "Membrecia.findByIdusermem", query = "SELECT m FROM Membrecia m WHERE m.idusermem = :idusermem"),
    @NamedQuery(name = "Membrecia.findByPuntos", query = "SELECT m FROM Membrecia m WHERE m.puntos = :puntos"),
    @NamedQuery(name = "Membrecia.findByFechamem", query = "SELECT m FROM Membrecia m WHERE m.fechamem = :fechamem"),
    @NamedQuery(name = "Membrecia.findByActivamem", query = "SELECT m FROM Membrecia m WHERE m.activamem = :activamem")})
public class Membrecia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idmem")
    private int idmem;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idusermem")
    private String idusermem;
    @Column(name = "puntos")
    private Integer puntos;
    @Column(name = "fechamem")
    @Temporal(TemporalType.DATE)
    private Date fechamem;
    @Column(name = "activamem")
    private Boolean activamem;

    public Membrecia() {
    }

    public Membrecia(String idusermem) {
        this.idusermem = idusermem;
    }

    public Membrecia(String idusermem, int idmem) {
        this.idusermem = idusermem;
        this.idmem = idmem;
    }

    public int getIdmem() {
        return idmem;
    }

    public void setIdmem(int idmem) {
        this.idmem = idmem;
    }

    public String getIdusermem() {
        return idusermem;
    }

    public void setIdusermem(String idusermem) {
        this.idusermem = idusermem;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Date getFechamem() {
        return fechamem;
    }

    public void setFechamem(Date fechamem) {
        this.fechamem = fechamem;
    }

    public Boolean getActivamem() {
        return activamem;
    }

    public void setActivamem(Boolean activamem) {
        this.activamem = activamem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusermem != null ? idusermem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membrecia)) {
            return false;
        }
        Membrecia other = (Membrecia) object;
        if ((this.idusermem == null && other.idusermem != null) || (this.idusermem != null && !this.idusermem.equals(other.idusermem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.RecetarioWeb.Entitys.Membrecia[ idusermem=" + idusermem + " ]";
    }
    
}
