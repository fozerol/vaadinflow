package com.mycompany.mavenproject3.entity.vehicle;

import com.mycompany.mavenproject3.entity.AbstractCompanyEntity_;
import com.mycompany.mavenproject3.entity.Customer;
import com.mycompany.mavenproject3.entity.vehicle.VehicleType;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-24T09:54:07")
@StaticMetamodel(Vehicle.class)
public class Vehicle_ extends AbstractCompanyEntity_ {

    public static volatile SingularAttribute<Vehicle, LocalDate> firstRegDate;
    public static volatile SingularAttribute<Vehicle, String> shortvin;
    public static volatile SingularAttribute<Vehicle, String> vin;
    public static volatile SingularAttribute<Vehicle, String> plateNumber;
    public static volatile SingularAttribute<Vehicle, VehicleType> type;
    public static volatile SingularAttribute<Vehicle, Customer> customer;

}