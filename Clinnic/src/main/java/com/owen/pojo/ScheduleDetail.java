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
@Table(name = "schedule_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScheduleDetail.findAll", query = "SELECT s FROM ScheduleDetail s"),
    @NamedQuery(name = "ScheduleDetail.findByDateSchedule", query = "SELECT s FROM ScheduleDetail s WHERE s.dateSchedule = :dateSchedule"),
    @NamedQuery(name = "ScheduleDetail.findById", query = "SELECT s FROM ScheduleDetail s WHERE s.id = :id"),
    @NamedQuery(name = "ScheduleDetail.findByStatus", query = "SELECT s FROM ScheduleDetail s WHERE s.status = :status")})
public class ScheduleDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "date_schedule")
    @Temporal(TemporalType.DATE)
    private Date dateSchedule;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private Short status;
    @JoinColumn(name = "shift_id", referencedColumnName = "id")
    @ManyToOne
    private Shift shiftId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    @Transient
    private Date[] listdate1;
    @Transient
    private Date[] listdate2;
    @Transient
    private Date[] listdate3;

    /**
     * @return the listdate
     */
    public Date[] getListdate1() {
        return listdate1;
    }

    /**
     * @param listdate the listdate to set
     */
    public void setListdate1(Date[] listdate1) {
        this.listdate1 = listdate1;
    }

    public Date[] getListdate2() {
        return listdate2;
    }

    /**
     * @param listdate2 the listdate2 to set
     */
    public void setListdate2(Date[] listdate2) {
        this.listdate2 = listdate2;
    }

    /**
     * @return the listdate3
     */
    public Date[] getListdate3() {
        return listdate3;
    }

    /**
     * @param listdate3 the listdate3 to set
     */
    public void setListdate3(Date[] listdate3) {
        this.listdate3 = listdate3;
    }



    public ScheduleDetail() {
    }

    public ScheduleDetail(Integer id) {
        this.id = id;
    }

    public Date getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(Date dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Shift getShiftId() {
        return shiftId;
    }

    public void setShiftId(Shift shiftId) {
        this.shiftId = shiftId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof ScheduleDetail)) {
            return false;
        }
        ScheduleDetail other = (ScheduleDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.owen.pojo.ScheduleDetail[ id=" + id + " ]";
    }
    
}
