/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.appdao.CompanyDao;
import com.mycompany.mavenproject3.entity.Company;
import com.mycompany.mavenproject3.genericbutton.GenericButtonGroup;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.melodion.Melodion;
import org.vaadin.melodion.Melodion.Tab;

/**
 *
 * @author fatih
 */
public class CompanyView extends GenericView<Company>{
        @Inject Company company;
        @Inject CompanyDao dao;
        private TextField name = new TextField("Company name");
        private TextField taxNumber = new TextField("Tax Number");
        private TextField address = new TextField("Company adress");
        private GenericButtonGroup<Company> genericbuttongroup;
        public CompanyView(){
            
        }
        @PostConstruct
        public void init(){
        this.setObject(company);
        this.grid.setItems( dao.findAll());
        this.binder.forField(name).bind(Company::getName,Company::setName);
        this.binder.forField(address).bind(Company::getAddress,Company::setAddress);
        this.binder.bindInstanceFields( this );

        genericbuttongroup = new GenericButtonGroup<>(dao,this);
       this.addComponents(name,taxNumber,address,genericbuttongroup,grid);
       grid.addItemClickListener(e->{
           this.setObject((Company) e.getItem());
       });
        }
}
