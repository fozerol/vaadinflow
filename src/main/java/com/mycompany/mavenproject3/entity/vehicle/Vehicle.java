/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity.vehicle;

import com.mycompany.mavenproject3.entity.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "vehicles")
@NamedQueries({
@NamedQuery(name="Vehicle.findAll", query="SELECT e FROM Vehicle e where 1=1"),
@NamedQuery(name="Vehicle.countAll", query="SELECT COUNT(e) FROM Vehicle e where 1=1"),
@NamedQuery(name="Vehicle.findAllByCompany", query="SELECT e FROM Vehicle e where e.company = :company")
})
@NamedNativeQuery(name="Vehicle.findAllWithTranslation",query = "SELECT s.id,s.code, case when ?languageid = 1 then translation1 when "
        + "?languageid = 2 then translation2 else code end as name ,s.translation1,translation2,version FROM customertypes s",resultClass = Vehicle.class)



public class Vehicle extends AbstractEntity implements Serializable {
            
            private String plateNumber;
            private Date firstRegDate;
            private String vin;
            private String shortvin;
            @OneToOne
            @JoinColumn(name = "companyid")
            private Company company;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getFirstRegDate() {
        return firstRegDate;
    }

    public void setFirstRegDate(Date firstRegDate) {
        this.firstRegDate = firstRegDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getShortvin() {
        return shortvin;
    }

    public void setShortvin(String shortvin) {
        this.shortvin = shortvin;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public Vehicle() {
        
        
    }
}
