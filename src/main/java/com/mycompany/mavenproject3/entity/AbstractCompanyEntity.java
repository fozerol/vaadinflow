/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import static com.mycompany.mavenproject3.AuthService.getUser;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author fatih
 */
@MappedSuperclass
public abstract class AbstractCompanyEntity implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private int version;
    @OneToOne
    @JoinColumn(name = "companyid")
    private Company company;

    public AbstractCompanyEntity() {
            
    }
    
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
  
}
