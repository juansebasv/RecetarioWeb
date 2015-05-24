/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByIdemp", query = "SELECT e FROM Empresa e WHERE e.idemp = :idemp"),
    @NamedQuery(name = "Empresa.findByNombreemp", query = "SELECT e FROM Empresa e WHERE e.nombreemp = :nombreemp"),
    @NamedQuery(name = "Empresa.findByDescripcionemp", query = "SELECT e FROM Empresa e WHERE e.descripcionemp = :descripcionemp"),
    @NamedQuery(name = "Empresa.findByImagenemp", query = "SELECT e FROM Empresa e WHERE e.imagenemp = :imagenemp")})
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idemp")
    private int idemp;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreemp")
    private String nombreemp;
    @Size(max = 1000)
    @Column(name = "descripcionemp")
    private String descripcionemp;
    @Size(max = 1000)
    @Column(name = "imagenemp")
    private String imagenemp;

    public Empresa() {
    }

    public Empresa(String nombreemp) {
        this.nombreemp = nombreemp;
    }

    public Empresa(String nombreemp, int idemp) {
        this.nombreemp = nombreemp;
        this.idemp = idemp;
    }

    public int getIdemp() {
        return idemp;
    }

    public void setIdemp(int idemp) {
        this.idemp = idemp;
    }

    public String getNombreemp() {
        return nombreemp;
    }

    public void setNombreemp(String nombreemp) {
        this.nombreemp = nombreemp;
    }

    public String getDescripcionemp() {
        return descripcionemp;
    }

    public void setDescripcionemp(String descripcionemp) {
        this.descripcionemp = descripcionemp;
    }

    public String getImagenemp() {
        return imagenemp;
    }

    public void setImagenemp(String imagenemp) {
        this.imagenemp = imagenemp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreemp != null ? nombreemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.nombreemp == null && other.nombreemp != null) || (this.nombreemp != null && !this.nombreemp.equals(other.nombreemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.RecetarioWeb.Entitys.Empresa[ nombreemp=" + nombreemp + " ]";
    }
    
}
