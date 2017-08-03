package com.mycompany.mavenproject3.entity.auth;

import com.mycompany.mavenproject3.entity.auth.Role;
import com.mycompany.mavenproject3.entity.auth.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-08-02T16:37:34")
@StaticMetamodel(UserRole.class)
public class UserRole_ { 

    public static volatile SingularAttribute<UserRole, Role> role;
    public static volatile SingularAttribute<UserRole, Integer> id;
    public static volatile SingularAttribute<UserRole, User> user;

}