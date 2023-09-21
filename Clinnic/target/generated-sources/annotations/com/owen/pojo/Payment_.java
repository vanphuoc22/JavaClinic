package com.owen.pojo;

import com.owen.pojo.Bill;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-12T12:16:56")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SetAttribute<Payment, Bill> billSet;
    public static volatile SingularAttribute<Payment, String> paymentMethod;
    public static volatile SingularAttribute<Payment, Integer> id;

}