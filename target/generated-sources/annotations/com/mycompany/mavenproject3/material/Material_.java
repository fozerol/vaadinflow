package com.mycompany.mavenproject3.material;

import com.mycompany.mavenproject3.entity.AbstractCompanyEntity_;
import com.mycompany.mavenproject3.material.MaterialClass;
import com.mycompany.mavenproject3.material.MaterialGroup;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-24T09:54:07")
@StaticMetamodel(Material.class)
public class Material_ extends AbstractCompanyEntity_ {

    public static volatile SingularAttribute<Material, MaterialClass> materialClass;
    public static volatile SingularAttribute<Material, String> code;
    public static volatile SingularAttribute<Material, MaterialGroup> materialGroup;
    public static volatile SingularAttribute<Material, String> gcode;

}