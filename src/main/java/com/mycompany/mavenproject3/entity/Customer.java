/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "customers")
@NamedQueries({
@NamedQuery(name="Customer.findAll", query="SELECT e FROM Customer e where 1=1"),
@NamedQuery(name="Customer.countAll", query="SELECT COUNT(e) FROM Customer e where 1=1")
})



public class Customer implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String surname;
private int taxnumber;
@OneToOne
@JoinColumn(name = "customertypeid")
private CustomerType type;
@OneToMany
(mappedBy = "customer",orphanRemoval=true, cascade = CascadeType.ALL)
private List<Address> addresses = new ArrayList<>();

    public List<Address> getAddresses() {
        if (addresses == null)
        {
            this.addresses= new ArrayList<Address>();
        }
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Customer () {
        
    }

    public Customer(String name, String surname, int taxnumber, CustomerType type) {
        this.name = name;
        this.surname = surname;
        this.taxnumber = taxnumber;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getTaxnumber() {
        return taxnumber;
    }

    public void setTaxnumber(int taxnumber) {
        this.taxnumber = taxnumber;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }
}
