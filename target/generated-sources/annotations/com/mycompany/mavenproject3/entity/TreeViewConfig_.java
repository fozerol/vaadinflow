package com.mycompany.mavenproject3.entity;

import com.mycompany.mavenproject3.entity.auth.AppRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-08T14:50:58")
@StaticMetamodel(TreeViewConfig.class)
public class TreeViewConfig_ { 

    public static volatile SingularAttribute<TreeViewConfig, String> nodeName;
    public static volatile SingularAttribute<TreeViewConfig, String> nodeCaption;
    public static volatile SingularAttribute<TreeViewConfig, Integer> hierarchy;
    public static volatile SingularAttribute<TreeViewConfig, Integer> id;
    public static volatile SingularAttribute<TreeViewConfig, Integer> position;
    public static volatile ListAttribute<TreeViewConfig, AppRole> appRoles;
    public static volatile SingularAttribute<TreeViewConfig, String> classFileName;

}