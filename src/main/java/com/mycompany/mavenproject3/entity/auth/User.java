/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity.auth;

import com.mycompany.mavenproject3.entity.Company;
import java.io.Serializable;
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
@Table(name = "users")
@NamedQueries({
@NamedQuery(name="User.findAll", query="SELECT e FROM User e where 1=1"),
@NamedQuery(name="User.countAll", query="SELECT COUNT(e) FROM User e where 1=1")
})



public class User implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String userName;
private String name;
private String surname;
private String email;
@OneToOne
@JoinColumn(name = "companyid")
private Company company;
@OneToMany
(mappedBy = "user", cascade = CascadeType.ALL)
private List<UserRole> userRoles;


    public User () {
        
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
