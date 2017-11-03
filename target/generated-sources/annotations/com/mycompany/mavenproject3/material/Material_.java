package com.mycompany.mavenproject3.material;

import com.mycompany.mavenproject3.entity.AbstractCompanyEntity_;
import com.mycompany.mavenproject3.material.MaterialClass;
import com.mycompany.mavenproject3.material.MaterialGroup;
import com.mycompany.mavenproject3.material.Unit;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-31T14:48:40")
@StaticMetamodel(Material.class)
public class Material_ extends AbstractCompanyEntity_ {

    public static volatile SingularAttribute<Material, MaterialClass> materialClass;
    public static volatile SingularAttribute<Material, Integer> taxrate;
    public static volatile SingularAttribute<Material, Integer> dfxdiscount;
    public static volatile SingularAttribute<Material, String> code;
    public static volatile SingularAttribute<Material, Float> dfxMinStockQuantity;
    public static volatile SingularAttribute<Material, String> hierarchy;
    public static volatile SingularAttribute<Material, String> gcode;
    public static volatile SingularAttribute<Material, Float> dfxStockQuantity;
    public static volatile SingularAttribute<Material, Unit> unit;
    public static volatile SingularAttribute<Material, Float> grossweight;
    public static volatile SingularAttribute<Material, Float> dfxMaxOrderQuantity;
    public static volatile SingularAttribute<Material, MaterialGroup> materialGroup;
    public static volatile SingularAttribute<Material, Float> netweight;
    public static volatile SingularAttribute<Material, Boolean> dfx;
    public static volatile SingularAttribute<Material, Float> minOrderQuantity;
    public static volatile SingularAttribute<Material, String> previousCode;

}