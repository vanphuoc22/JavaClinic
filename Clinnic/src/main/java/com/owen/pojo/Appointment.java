/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Trinh Bao Duy
 */
@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findById", query = "SELECT a FROM Appointment a WHERE a.id = :id"),
    @NamedQuery(name = "Appointment.findByAppointmentDate", query = "SELECT a FROM Appointment a WHERE a.appointmentDate = :appointmentDate"),
    @NamedQuery(name = "Appointment.findByStatus", query = "SELECT a FROM Appointment a WHERE a.status = :status"),
    @NamedQuery(name = "Appointment.findByMedicalappointmentDate", query = "SELECT a FROM Appointment a WHERE a.medicalappointmentDate = :medicalappointmentDate")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "appointment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;
    @Column(name = "status")
    private Short status;
    @Column(name = "medicalappointment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date medicalappointmentDate;
    @OneToMany(mappedBy = "appoId")
    @JsonIgnore
    private Set<Bill> billSet;
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    @ManyToOne
    private Prescription prescriptionId;
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @ManyToOne
    private User doctorId;
    @JoinColumn(name = "nurse_id", referencedColumnName = "id")
    @ManyToOne
    private User nurseId;
    @JoinColumn(name = "sickperson_id", referencedColumnName = "id")
    @ManyToOne
    private User sickpersonId;
    @OneToMany(mappedBy = "appoId")
    @JsonIgnore
    private Set<ServiceItems> serviceItemsSet;

    public Appointment() {
    }

    public Appointment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getMedicalappointmentDate() {
        return medicalappointmentDate;
    }

    public void setMedicalappointmentDate(Date medicalappointmentDate) {
        this.medicalappointmentDate = medicalappointmentDate;
    }

    @XmlTransient
    public Set<Bill> getBillSet() {
        return billSet;
    }

    public void setBillSet(Set<Bill> billSet) {
        this.billSet = billSet;
    }

    public Prescription getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Prescription prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public User getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(User doctorId) {
        this.doctorId = doctorId;
    }

    public User getNurseId() {
        return nurseId;
    }

    public void setNurseId(User nurseId) {
        this.nurseId = nurseId;
    }

    public User getSickpersonId() {
        return sickpersonId;
    }

    public void setSickpersonId(User sickpersonId) {
        this.sickpersonId = sickpersonId;
    }

    @XmlTransient
    public Set<ServiceItems> getServiceItemsSet() {
        return serviceItemsSet;
    }

    public void setServiceItemsSet(Set<ServiceItems> serviceItemsSet) {
        this.serviceItemsSet = serviceItemsSet;
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
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.owen.pojo.Appointment[ id=" + id + " ]";
    }
    
}

