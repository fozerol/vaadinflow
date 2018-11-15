/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "customeraddress")
@NamedQueries({
@NamedQuery(name="CustomerAddress.findAll", query="SELECT e FROM City e where 1=1"),
@NamedQuery(name="CustomerAddress.findByCountry", query="SELECT e FROM City e where e.country = :country"),
@NamedQuery(name="CustomerAddress.countAll", query="SELECT COUNT(e) FROM City e")
})
class CustomerAddress extends AbstractEntityi18n {
    @OneToOne
    @JoinColumn(name="addressid")
    private Address address;
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name="customerid",referencedColumnName = "id")
    private Customer customer;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}
