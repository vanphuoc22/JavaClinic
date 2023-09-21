package com.owen.pojo;

import com.owen.pojo.Appointment;
import com.owen.pojo.Role;
import com.owen.pojo.ScheduleDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-12T12:16:56")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile SingularAttribute<User, String> sex;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SetAttribute<User, Appointment> appointmentSet;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> emaill;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Date> dod;
    public static volatile SetAttribute<User, Appointment> appointmentSet2;
    public static volatile SetAttribute<User, Appointment> appointmentSet1;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SetAttribute<User, ScheduleDetail> scheduleDetailSet;
    public static volatile SingularAttribute<User, String> username;

}