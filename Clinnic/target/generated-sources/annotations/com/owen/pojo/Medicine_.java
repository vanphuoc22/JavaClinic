package com.owen.pojo;

import com.owen.pojo.PrescriptionItem;
import com.owen.pojo.Unit;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-12T12:16:56")
@StaticMetamodel(Medicine.class)
public class Medicine_ { 

    public static volatile SingularAttribute<Medicine, Date> productionDate;
    public static volatile SingularAttribute<Medicine, Integer> quantity;
    public static volatile SingularAttribute<Medicine, String> provider;
    public static volatile SingularAttribute<Medicine, BigDecimal> price;
    public static volatile SingularAttribute<Medicine, String> name;
    public static volatile SingularAttribute<Medicine, Unit> idUnit;
    public static volatile SingularAttribute<Medicine, Integer> id;
    public static volatile SetAttribute<Medicine, PrescriptionItem> prescriptionItemSet;
    public static volatile SingularAttribute<Medicine, Date> expirationDate;

}