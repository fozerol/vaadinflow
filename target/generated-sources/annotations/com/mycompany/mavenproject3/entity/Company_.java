package com.mycompany.mavenproject3.entity;

import com.mycompany.mavenproject3.entity.Address;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-22T16:59:51")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile ListAttribute<Company, Address> addresses;
    public static volatile SingularAttribute<Company, String> address;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile SingularAttribute<Company, Long> taxNumber;
    public static volatile SingularAttribute<Company, Integer> id;

}