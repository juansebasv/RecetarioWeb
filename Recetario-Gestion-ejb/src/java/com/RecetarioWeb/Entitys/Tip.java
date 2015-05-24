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
@Table(name = "tip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tip.findAll", query = "SELECT t FROM Tip t"),
    @NamedQuery(name = "Tip.findByIdtip", query = "SELECT t FROM Tip t WHERE t.idtip = :idtip"),
    @NamedQuery(name = "Tip.findByIdusertip", query = "SELECT t FROM Tip t WHERE t.idusertip = :idusertip"),
    @NamedQuery(name = "Tip.findByNombretip", query = "SELECT t FROM Tip t WHERE t.nombretip = :nombretip"),
    @NamedQuery(name = "Tip.findByDescripciontip", query = "SELECT t FROM Tip t WHERE t.descripciontip = :descripciontip"),
    @NamedQuery(name = "Tip.findByFechatip", query = "SELECT t FROM Tip t WHERE t.fechatip = :fechatip"),
    @NamedQuery(name = "Tip.findByAutortip", query = "SELECT t FROM Tip t WHERE t.autortip = :autortip")})
public class Tip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idtip")
    private int idtip;
    @Size(max = 100)
    @Column(name = "idusertip")
    private String idusertip;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombretip")
    private String nombretip;
    @Size(max = 1000)
    @Column(name = "descripciontip")
    private String descripciontip;
    @Column(name = "fechatip")
    @Temporal(TemporalType.DATE)
    private Date fechatip;
    @Size(max = 100)
    @Column(name = "autortip")
    private String autortip;

    public Tip() {
    }

    public Tip(String nombretip) {
        this.nombretip = nombretip;
    }

    public Tip(String nombretip, int idtip) {
        this.nombretip = nombretip;
        this.idtip = idtip;
    }

    public int getIdtip() {
        return idtip;
    }

    public void setIdtip(int idtip) {
        this.idtip = idtip;
    }

    public String getIdusertip() {
        return idusertip;
    }

    public void setIdusertip(String idusertip) {
        this.idusertip = idusertip;
    }

    public String getNombretip() {
        return nombretip;
    }

    public void setNombretip(String nombretip) {
        this.nombretip = nombretip;
    }

    public String getDescripciontip() {
        return descripciontip;
    }

    public void setDescripciontip(String descripciontip) {
        this.descripciontip = descripciontip;
    }

    public Date getFechatip() {
        return fechatip;
    }

    public void setFechatip(Date fechatip) {
        this.fechatip = fechatip;
    }

    public String getAutortip() {
        return autortip;
    }

    public void setAutortip(String autortip) {
        this.autortip = autortip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombretip != null ? nombretip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tip)) {
            return false;
        }
        Tip other = (Tip) object;
        if ((this.nombretip == null && other.nombretip != null) || (this.nombretip != null && !this.nombretip.equals(other.nombretip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.RecetarioWeb.Entitys.Tip[ nombretip=" + nombretip + " ]";
    }
    
}
