/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.appdao;


import com.mycompany.mavenproject3.entity.auth.UserRole;
import genericdao.GenericDaoImp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author fatih
 */
@Stateless 
public class UserRoleDao extends GenericDaoImp<UserRole>{
    public UserRoleDao() {
        super.setType(UserRole.class);
    }
    public UserRoleDao(Class type){
        super.setType(type);
    }
    public void setEntityManager(EntityManager em){
        super.setEm(em);
    }
}
