/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.appdao.auth;


import com.mycompany.mavenproject3.entity.auth.Role;
import genericdao.GenericDaoImp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author fatih
 */
@Stateless 
public class RoleDao extends GenericDaoImp<Role>{
    public RoleDao() {
        super.setType(Role.class);
    }
    public RoleDao(Class type){
        super.setType(type);
    }
    public void setEntityManager(EntityManager em){
        super.setEm(em);
    }
}
