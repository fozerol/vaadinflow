package com.mycompany.mavenproject3.entity.vehicle;

import com.mycompany.mavenproject3.entity.AbstractEntity_;
import com.mycompany.mavenproject3.entity.Company;
import com.mycompany.mavenproject3.entity.vehicle.VehicleType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T17:25:51")
@StaticMetamodel(Vehicle.class)
public class Vehicle_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Vehicle, Date> firstRegDate;
    public static volatile SingularAttribute<Vehicle, String> shortvin;
    public static volatile SingularAttribute<Vehicle, String> vin;
    public static volatile SingularAttribute<Vehicle, Company> company;
    public static volatile SingularAttribute<Vehicle, String> plateNumber;
    public static volatile SingularAttribute<Vehicle, VehicleType> type;

}