/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdao;

import static com.mycompany.mavenproject3.AuthService.getLanguage;
import com.mycompany.mavenproject3.MyUI;
import com.mycompany.mavenproject3.view.generalform.GenericModalForm;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;


/**
 *
 * @author fatih
 */
@Stateless
@Named("baseDao")
public  class GenericDaoImp<T>{// implements GenericDao<T>{
    
    @PersistenceContext(unitName = "pu")
    protected EntityManager em;    
    private Class<T> type;
    public void setType(Class type) {
        this.type = type;
    }

    public GenericDaoImp() {
    }
    
   @PostConstruct
   public void init(){
       
     
   }
    public void setEm(EntityManager em) {
        this.em = em;
    }

    //@Override
    public long countAll(){
        long count = (long) em.createNamedQuery(type.getSimpleName()+".countAll").getSingleResult();
        return count;
    }
    

    //@Override
    @Transactional
    public T create(T t) {
        t = em.merge(t);
        this.em.persist(t);
                return t;
        
    }
    //@Override
    @Transactional
    public void delete(T t) {
        t = em.merge(t);
        em.remove(t);
    }

    //@Override
    public T find(Object id) {
        return this.em.find(type, id);
                //find(type, id);
    }
    public List<T> findAll()
    {
        
        List<T> result = em.createNamedQuery(type.getSimpleName()+".findAll").getResultList();
        return result;
    }
    
    //@Override
    public T update(T t) {
        this.em.merge(t);
        return t;
    }

    //@Override
    public T findById(int id) {
        List<T> result = em.createNamedQuery(type.getSimpleName()+".findById").setParameter("id", id).getResultList();
        if (result.size() == 0){
            return null;
        }
        else{
        return result.iterator().next();
        }
    }
    
    public List<T> findAllWithTranslation() {
        List<T> result = new ArrayList<>();
            //result  = em.createNamedQuery(type.getSimpleName()+".findAllWithTranslation").setParameter(1, getLanguage().getId() ).getResultList();
            int i = getLanguage().getId();
            result  = em.createNamedQuery(type.getSimpleName() + ".findAllWithTranslation").setParameter("languageid", getLanguage().getId()).getResultList();
        if (result.size() == 0){
            return null;
        }
        else{
        return result;
        }
    }
}
