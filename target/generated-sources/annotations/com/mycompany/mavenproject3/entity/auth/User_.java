package com.mycompany.mavenproject3.entity.auth;

import com.mycompany.mavenproject3.entity.Company;
import com.mycompany.mavenproject3.entity.auth.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-20T12:16:41")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, UserRole> userRoles;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Company> company;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, String> email;

}