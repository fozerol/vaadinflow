package com.mycompany.mavenproject3.entity.auth;

import com.mycompany.mavenproject3.entity.TreeViewConfig;
import com.mycompany.mavenproject3.entity.auth.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-21T12:02:33")
@StaticMetamodel(AppRole.class)
public class AppRole_ { 

    public static volatile SingularAttribute<AppRole, Role> role;
    public static volatile SingularAttribute<AppRole, TreeViewConfig> treeviewconfig;
    public static volatile SingularAttribute<AppRole, String> name;
    public static volatile SingularAttribute<AppRole, Integer> id;

}