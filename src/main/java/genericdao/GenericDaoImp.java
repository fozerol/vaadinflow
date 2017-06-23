/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdao;

import com.mycompany.mavenproject3.MyUI;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;


/**
 *
 * @author fatih
 */
//@Stateless
public  class GenericDaoImp<T> implements GenericDao<T>{
    
    @PersistenceContext(unitName = "pu")
    private EntityManager em;    
    private Class<T> type;
    public void setType(Class type) {
        this.type = type;
    }
    
    public GenericDaoImp(){

   }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public long countAll(){
        long count = (long) em.createNamedQuery(type.getSimpleName()+".countAll").getSingleResult();
        return count;
    }
    

    @Override
    public T create(T t) {
        this.em.persist(t);
        return t;
        
    }
    @Override
    public void delete(T t) {
        t = em.merge(t);
        em.remove(t);
    }

    @Override
    public T find(Object id) {
        return this.em.find(type, id);
                //find(type, id);
    }
    public List<T> findAll()
    {
        
        List<T> result = em.createNamedQuery(type.getSimpleName()+".findAll").getResultList();
        return result;
    }
    
    @Override
    public T update(T t) {
        this.em.merge(t);
        return t;
    }
    
    
}
