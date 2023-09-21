/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trinh Bao Duy
 */
@Entity
@Table(name = "service_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceItems.findAll", query = "SELECT s FROM ServiceItems s"),
    @NamedQuery(name = "ServiceItems.findByDateSer", query = "SELECT s FROM ServiceItems s WHERE s.dateSer = :dateSer"),
    @NamedQuery(name = "ServiceItems.findById", query = "SELECT s FROM ServiceItems s WHERE s.id = :id")})
public class ServiceItems implements Serializable {



    private static final long serialVersionUID = 1L;
    @Column(name = "date_ser")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSer;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "appo_id", referencedColumnName = "id")
    @ManyToOne
    private Appointment appoId;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne
    private Service serviceId;
    @Transient
    private Service[] listdichvu;
        /**
     * @return the listdichvu
     */
    public Service[] getListdichvu() {
        return listdichvu;
    }

    /**
     * @param listdichvu the listdichvu to set
     */
    public void setListdichvu(Service[] listdichvu) {
        this.listdichvu = listdichvu;
    }
    
    public ServiceItems() {
    }

    public ServiceItems(Integer id) {
        this.id = id;
    }

    public Date getDateSer() {
        return dateSer;
    }

    public void setDateSer(Date dateSer) {
        this.dateSer = dateSer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Appointment getAppoId() {
        return appoId;
    }

    public void setAppoId(Appointment appoId) {
        this.appoId = appoId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
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
        if (!(object instanceof ServiceItems)) {
            return false;
        }
        ServiceItems other = (ServiceItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.owen.pojo.ServiceItems[ id=" + id + " ]";
    }
    
}
