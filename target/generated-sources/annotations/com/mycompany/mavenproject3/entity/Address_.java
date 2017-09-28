package com.mycompany.mavenproject3.entity;

import com.mycompany.mavenproject3.entity.City;
import com.mycompany.mavenproject3.entity.Company;
import com.mycompany.mavenproject3.entity.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-22T16:59:51")
@StaticMetamodel(Address.class)
public class Address_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Address, String> zipcode;
    public static volatile SingularAttribute<Address, String> address;
    public static volatile SingularAttribute<Address, City> city;
    public static volatile SingularAttribute<Address, Company> company;
    public static volatile SingularAttribute<Address, Customer> customer;

}