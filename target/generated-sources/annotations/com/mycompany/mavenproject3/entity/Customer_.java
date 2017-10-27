package com.mycompany.mavenproject3.entity;

import com.mycompany.mavenproject3.entity.Address;
import com.mycompany.mavenproject3.entity.CustomerType;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-24T09:54:07")
@StaticMetamodel(Customer.class)
public class Customer_ extends AbstractCompanyEntity_ {

    public static volatile ListAttribute<Customer, Address> addresses;
    public static volatile SingularAttribute<Customer, String> surname;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile SingularAttribute<Customer, Integer> taxnumber;
    public static volatile SingularAttribute<Customer, CustomerType> type;

}