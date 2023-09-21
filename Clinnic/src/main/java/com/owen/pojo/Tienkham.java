/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Trinh Bao Duy
 */
@Entity
@Table(name = "tienkham")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tienkham.findAll", query = "SELECT t FROM Tienkham t"),
    @NamedQuery(name = "Tienkham.findById", query = "SELECT t FROM Tienkham t WHERE t.id = :id"),
    @NamedQuery(name = "Tienkham.findByTienkham", query = "SELECT t FROM Tienkham t WHERE t.tienkham = :tienkham")})
public class Tienkham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "tienkham")
    private Integer tienkham;
    @OneToMany(mappedBy = "tienkham")
    private Set<Bill> billSet;

    public Tienkham() {
    }

    public Tienkham(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTienkham() {
        return tienkham;
    }

    public void setTienkham(Integer tienkham) {
        this.tienkham = tienkham;
    }

    @XmlTransient
    public Set<Bill> getBillSet() {
        return billSet;
    }

    public void setBillSet(Set<Bill> billSet) {
        this.billSet = billSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienkham)) {
            return false;
        }
        Tienkham other = (Tienkham) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.owen.pojo.Tienkham[ id=" + id + " ]";
    }
    
}
