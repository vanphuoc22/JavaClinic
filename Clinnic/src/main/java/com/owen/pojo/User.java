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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Trinh Bao Duy
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
    @NamedQuery(name = "User.findByEmaill", query = "SELECT u FROM User u WHERE u.emaill = :emaill"),
    @NamedQuery(name = "User.findByDod", query = "SELECT u FROM User u WHERE u.dod = :dod"),
    @NamedQuery(name = "User.findBySex", query = "SELECT u FROM User u WHERE u.sex = :sex")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1000, min = 1, message = "{user.notnull}")
//    @UniqueElements( message = "{user.username.unique}")
    @Column(name = "username", unique = true)
    @NotNull(message = "{user.notnull}")
    private String username;
    @Size(max = 1000, min = 1, message = "{user.notnull}")
    @Column(name = "password")
    private String password;
//    @Size(max = 1000, min = 1, message = "{user.notnull}")
    @Column(name = "avatar")
    private String avatar;
    @NotNull(message = "{user.name.notNull}")
    @Size(min = 5, max = 20, message = "{user.name.leng}")
    @Column(name = "name")
    private String name;
//    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45, min = 1, message = "{user.notnull}")
    @Column(name = "phone")
    private String phone;
    @Size(max = 100, min = 1, message = "{user.notnull}")
    @NotNull(message = "{user.notnull}")
    @Column(name = "address")
    private String address;
    @Size(max = 100, min = 1, message = "{user.notnull}")
    @Column(name = "emaill")
    @NotNull(message = "{user.notnull}")
    private String emaill;
    @Column(name = "dod")
    @Temporal(TemporalType.DATE)
//    @NotNull(message = "{user.notnull}")
//    @Past(message = "{user.dod.past}")
    private Date dod;
    @Size(max = 45)
    @Column(name = "sex")
    private String sex;
    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private Set<ScheduleDetail> scheduleDetailSet;
    @OneToMany(mappedBy = "doctorId")
    @JsonIgnore
    private Set<Appointment> appointmentSet;
    @OneToMany(mappedBy = "nurseId")
    @JsonIgnore
    private Set<Appointment> appointmentSet1;
    @OneToMany(mappedBy = "sickpersonId")
    @JsonIgnore
    private Set<Appointment> appointmentSet2;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role roleId;

    @Transient
    @JsonIgnore
//    @Size(max = 1000 , min = 1,message = "{user.notnull}")
    private MultipartFile file;

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public Date getDod() {
        return dod;
    }

    public void setDod(Date dod) {
        this.dod = dod;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @XmlTransient
    public Set<ScheduleDetail> getScheduleDetailSet() {
        return scheduleDetailSet;
    }

    public void setScheduleDetailSet(Set<ScheduleDetail> scheduleDetailSet) {
        this.scheduleDetailSet = scheduleDetailSet;
    }

    @XmlTransient
    public Set<Appointment> getAppointmentSet() {
        return appointmentSet;
    }

    public void setAppointmentSet(Set<Appointment> appointmentSet) {
        this.appointmentSet = appointmentSet;
    }

    @XmlTransient
    public Set<Appointment> getAppointmentSet1() {
        return appointmentSet1;
    }

    public void setAppointmentSet1(Set<Appointment> appointmentSet1) {
        this.appointmentSet1 = appointmentSet1;
    }

    @XmlTransient
    public Set<Appointment> getAppointmentSet2() {
        return appointmentSet2;
    }

    public void setAppointmentSet2(Set<Appointment> appointmentSet2) {
        this.appointmentSet2 = appointmentSet2;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.owen.pojo.User[ id=" + id + " ]";
    }

//    public String getUserRole() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}



