/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdao;

import java.util.Map;

/**
 *
 * @author fatih
 */
public interface GenericDao<T> {

    long countAll();
    T create(T t);
    void delete(T t);
    T find(Object id);
    T update(T t); 
    T findById(int id);
}
