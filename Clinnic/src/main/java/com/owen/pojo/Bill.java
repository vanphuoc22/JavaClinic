/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.pojo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trinh Bao Duy
 */
@Entity
@Table(name = "bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
    @NamedQuery(name = "Bill.findByPayMoney", query = "SELECT b FROM Bill b WHERE b.payMoney = :payMoney")})
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "pay_money")
    private Integer payMoney;
    @JoinColumn(name = "appo_id", referencedColumnName = "id")
    @ManyToOne
    private Appointment appoId;
    @JoinColumn(name = "pay_id", referencedColumnName = "id")
    @ManyToOne
    private Payment payId;
    @JoinColumn(name = "tienkham", referencedColumnName = "id")
    @ManyToOne
    private Tienkham tienkham;

    public Bill() {
    }

    public Bill(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Appointment getAppoId() {
        return appoId;
    }

    public void setAppoId(Appointment appoId) {
        this.appoId = appoId;
    }

    public Payment getPayId() {
        return payId;
    }

    public void setPayId(Payment payId) {
        this.payId = payId;
    }

    public Tienkham getTienkham() {
        return tienkham;
    }

    public void setTienkham(Tienkham tienkham) {
        this.tienkham = tienkham;
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
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.owen.pojo.Bill[ id=" + id + " ]";
    }
    
}
