/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "addresses")
@NamedQueries({@NamedQuery(name="Company.findAll", query="SELECT e FROM Company e"),@NamedQuery(name="Company.countAll", query="SELECT COUNT(e) FROM Company e")}
)

public class Address extends AbstractEntity implements Serializable {
    private String address;
    private String zipcode;
    @OneToOne
    @JoinColumn(name = "cityid")
    private City city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
    
public Address(){

}  
   
}

