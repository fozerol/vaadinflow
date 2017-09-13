/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.appdao.address;


import com.mycompany.mavenproject3.entity.City;
import com.mycompany.mavenproject3.entity.Country;
import genericdao.GenericDaoImp;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author fatih
 */
@Stateless 
public class CityDao extends GenericDaoImp<City>{
    public CityDao() {
        super.setType(City.class);
    }
    public CityDao(Class type){
        super.setType(type);
    }
    public void setEntityManager(EntityManager em){
        super.setEm(em);
    }
    public List<City> findByCountry(Country country){
        List<City> result = em.createNamedQuery("City.findByCountry").setParameter("country", country).getResultList();
        if (result.size() == 0){
            return null;
        }
        else{
        return result;
        }
        
    }
}
