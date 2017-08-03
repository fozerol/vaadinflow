/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.appdao.GenericObject;
import com.mycompany.mavenproject3.appdao.auth.RoleDao;
import com.mycompany.mavenproject3.entity.auth.Role;
import com.mycompany.mavenproject3.genericbutton.GenericButtonGroup;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author fatih
 */
public class RoleView extends GenericView<Role> implements View {
    @Inject Role role;
    @Inject RoleDao dao;
    private CheckBox status = new CheckBox("Active");
    //private Grid grid = new Grid<>(Role.class);
    private TextField name = new TextField("Role name");
    //private Binder<Role> binder = new Binder<>(Role.class);
    private GenericButtonGroup<Role> genericbuttongroup;

    public RoleView() {
        
    }
    @PostConstruct
    public void init(){
       this.setObject(role);
       grid.setItems( dao.findAll());
       binder.forField(name).bind(Role::getName,Role::setName);
       binder.forField(status).bind(Role::isStatus,Role::setStatus);
       binder.bindInstanceFields( this );
       //binder.setBean(role);
       //genericbuttongroup = new GenericButtonGroup<>(Role.class,roledao,binder,role,grid,this);
       genericbuttongroup = new GenericButtonGroup<>(dao,this);
       this.addComponents(name,status,genericbuttongroup,grid);
       grid.addItemClickListener(e->{
           this.setObject((Role) e.getItem());
       });
       
    }
    /*public void setObject(Object o){
        this.role = (Role) o;
        binder.setBean(role);
    }
    public Role  getObject(){
        return role;
    }
    public Role getNewInstance(){
        return new Role();
    }*/
    
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
