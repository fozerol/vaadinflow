/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.appdao.GenericObject;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import genericdao.GenericDaoImp;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.vaadin.gridutil.GridUtil;
import org.vaadin.gridutil.cell.*;
import org.vaadin.gridutil.converter.SimpleStringConverter;
import org.vaadin.gridutil.renderer.*;



/**
 *
 * @author fatih
 */
public  class GenericView<T> extends VerticalLayout implements GenericObject,View{
    protected Class<T> classtype;
    protected T t;
    protected Binder<T> binder;
    protected Grid<T> grid;

    boolean valid;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    
    public GenericView(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.classtype = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        binder = new Binder(classtype);
//        grid = new genGrid()
        grid = new Grid(classtype);
    }
/*    @PostConstruct
    public void init(){
    }*/
    
    
    public Class<T> getClasstype() {
        return classtype;
    }

    public void setClasstype(Class<T> classtype) {
        this.classtype = classtype;
    }

    
    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }



    @Override
    public void setObject(Object o) {
        this.t = (T) o;
        binder.setBean(t);
    }

    @Override
    public Object getObject() {
        return t;
    }

    @Override
    public Object getNewInstance() {
        T t = null;
        try {
           t =  classtype.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    @Override
    public Class<?> getType() {
        return this.classtype;
    }

    @Override
    public Binder getBinder() {
        return this.binder;
    }

    @Override
    public Grid getGrid() {
        return this.grid;
    }
}
