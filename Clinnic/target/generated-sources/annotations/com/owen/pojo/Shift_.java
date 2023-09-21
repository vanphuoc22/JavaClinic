package com.owen.pojo;

import com.owen.pojo.ScheduleDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-12T12:16:56")
@StaticMetamodel(Shift.class)
public class Shift_ { 

    public static volatile SingularAttribute<Shift, String> name;
    public static volatile SingularAttribute<Shift, Date> start;
    public static volatile SingularAttribute<Shift, Date> end;
    public static volatile SingularAttribute<Shift, Integer> id;
    public static volatile SetAttribute<Shift, ScheduleDetail> scheduleDetailSet;

}