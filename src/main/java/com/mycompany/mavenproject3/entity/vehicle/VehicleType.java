/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity.vehicle;

import com.mycompany.mavenproject3.entity.AbstractEntityTranslation;
import com.mycompany.mavenproject3.entity.genericdefination.DefinationClass;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "vehicletypes")
@NamedQueries({
@NamedQuery(name="VehicleType.findAll", query="SELECT e FROM VehicleType e"),
@NamedQuery(name="VehicleType.countAll", query="SELECT COUNT(e) FROM VehicleType e")})
@NamedNativeQuery(name="VehicleType.findAllWithTranslation",query = "SELECT s.id,s.code, case when ?languageid = 1 then translation1 when "
        + "?languageid = 2 then translation2 else code end as name ,s.translation1,translation2,version FROM customertypes s",resultClass = VehicleType.class)



public class VehicleType extends AbstractEntityTranslation implements Serializable,DefinationClass {

    public VehicleType() {
    }
}
