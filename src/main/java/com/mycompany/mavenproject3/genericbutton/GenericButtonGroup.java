/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.genericbutton;

import com.mycompany.mavenproject3.appdao.GenericObject;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import genericdao.GenericDaoImp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatih
 */
public class  GenericButtonGroup<T> extends HorizontalLayout{
    private Button saveButton = new Button("Save");
    private Button newButton = new Button("New");
    private Button deleteButton = new  Button("Delete");
    
       
    
    public GenericButtonGroup(Class<T> beanType,GenericDaoImp dao,Binder<T> binder,T t,Grid grid,GenericObject o) {
        this.addComponents(saveButton,newButton,deleteButton);
        saveButton.addClickListener(e->{
           /* try {
                binder.writeBean(t);
            } catch (ValidationException ex) {
                Logger.getLogger(GenericButtonGroup.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            dao.create(o.getObject());
            grid.setItems(dao.findAll());
        });
        newButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                T t = null;
                try {
                    t = beanType.newInstance();
                } catch (InstantiationException ex) {
                    Logger.getLogger(GenericButtonGroup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(GenericButtonGroup.class.getName()).log(Level.SEVERE, null, ex);
                }
                o.setObject(t);
                binder.readBean(t);
            }
        });
        deleteButton.addClickListener(e->{
            dao.delete(o.getObject());
            grid.setItems(dao.findAll());  
        });
    }
    
    
}
