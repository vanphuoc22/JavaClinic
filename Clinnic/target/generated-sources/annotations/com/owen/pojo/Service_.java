package com.owen.pojo;

import com.owen.pojo.ServiceItems;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-12T12:16:56")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile SetAttribute<Service, ServiceItems> serviceItemsSet;
    public static volatile SingularAttribute<Service, Long> price;
    public static volatile SingularAttribute<Service, String> name;
    public static volatile SingularAttribute<Service, Integer> id;

}