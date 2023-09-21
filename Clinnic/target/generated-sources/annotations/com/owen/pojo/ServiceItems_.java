package com.owen.pojo;

import com.owen.pojo.Appointment;
import com.owen.pojo.Service;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-12T12:16:56")
@StaticMetamodel(ServiceItems.class)
public class ServiceItems_ { 

    public static volatile SingularAttribute<ServiceItems, Date> dateSer;
    public static volatile SingularAttribute<ServiceItems, Integer> id;
    public static volatile SingularAttribute<ServiceItems, Service> serviceId;
    public static volatile SingularAttribute<ServiceItems, Appointment> appoId;

}