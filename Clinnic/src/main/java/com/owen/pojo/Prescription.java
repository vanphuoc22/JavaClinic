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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Trinh Bao Duy
 */
@Entity
@Table(name = "prescription")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prescription.findAll", query = "SELECT p FROM Prescription p"),
    @NamedQuery(name = "Prescription.findById", query = "SELECT p FROM Prescription p WHERE p.id = :id"),
    @NamedQuery(name = "Prescription.findByPrescriptionDate", query = "SELECT p FROM Prescription p WHERE p.prescriptionDate = :prescriptionDate"),
    @NamedQuery(name = "Prescription.findByPrescriptioncol", query = "SELECT p FROM Prescription p WHERE p.prescriptioncol = :prescriptioncol"),
    @NamedQuery(name = "Prescription.findBySymptom", query = "SELECT p FROM Prescription p WHERE p.symptom = :symptom"),
    @NamedQuery(name = "Prescription.findByConclusion", query = "SELECT p FROM Prescription p WHERE p.conclusion = :conclusion")})
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "prescription_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prescriptionDate;
    @Size(max = 45)
    @Column(name = "prescriptioncol")
    private String prescriptioncol;
    @Size(max = 45)
    @Column(name = "symptom")
    private String symptom;
    @Size(max = 45)
    @Column(name = "conclusion")
    private String conclusion;
    @OneToMany(mappedBy = "prescriptionId")
    @JsonIgnore
    private Set<Appointment> appointmentSet;
    @OneToMany(mappedBy = "prescriptionId")
    @JsonIgnore
    private Set<PrescriptionItem> prescriptionItemSet;

    public Prescription() {
    }

    public Prescription(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getPrescriptioncol() {
        return prescriptioncol;
    }

    public void setPrescriptioncol(String prescriptioncol) {
        this.prescriptioncol = prescriptioncol;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    @XmlTransient
    public Set<Appointment> getAppointmentSet() {
        return appointmentSet;
    }

    public void setAppointmentSet(Set<Appointment> appointmentSet) {
        this.appointmentSet = appointmentSet;
    }

    @XmlTransient
    public Set<PrescriptionItem> getPrescriptionItemSet() {
        return prescriptionItemSet;
    }

    public void setPrescriptionItemSet(Set<PrescriptionItem> prescriptionItemSet) {
        this.prescriptionItemSet = prescriptionItemSet;
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
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.owen.pojo.Prescription[ id=" + id + " ]";
    }
    
}
