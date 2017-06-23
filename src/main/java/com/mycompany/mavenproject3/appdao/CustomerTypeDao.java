/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.appdao;


import com.mycompany.mavenproject3.entity.CustomerType;
import genericdao.GenericDaoImp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author fatih
 */
@Stateless 
public class CustomerTypeDao extends GenericDaoImp<CustomerType>{
    public CustomerTypeDao() {
        super.setType(CustomerType.class);
    }
    public CustomerTypeDao(Class type){
        super.setType(type);
    }
    public void setEntityManager(EntityManager em){
        super.setEm(em);
    }
}
