/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.appdao;

import com.mycompany.mavenproject3.entity.FatihClass;
import genericdao.GenericDaoImp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fatih
 */
@Stateless 
public class FatihClassDao extends GenericDaoImp<FatihClass>{
    public FatihClassDao() {
        super.setType(FatihClass.class);
    }
    public FatihClassDao(Class type){
        super.setType(type);
    }
    public void setEntityManager(EntityManager em){
        super.setEm(em);
    }
}
