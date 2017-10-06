package com.mycompany.mavenproject3.entity;

import com.mycompany.mavenproject3.entity.Address;
import com.mycompany.mavenproject3.entity.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T17:25:51")
@StaticMetamodel(CustomerAddress.class)
 class CustomerAddress_ extends AbstractEntity_ {

    public static volatile SingularAttribute<CustomerAddress, Address> address;
    public static volatile SingularAttribute<CustomerAddress, Customer> customer;

}