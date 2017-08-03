/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.appdao.CustomerTypeDao;
import com.mycompany.mavenproject3.appdao.TreeViewDao;
import com.mycompany.mavenproject3.appdao.auth.RoleDao;
import com.mycompany.mavenproject3.customcomponent.FTwinColSelect;
import com.mycompany.mavenproject3.entity.CustomerType;
import com.mycompany.mavenproject3.entity.TreeViewConfig;
import com.mycompany.mavenproject3.entity.auth.User;
import com.mycompany.mavenproject3.entity.auth.Role;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author fatih
 */
public class TestView extends GenericView<User>{
        @Inject RoleDao roledao;
        @Inject TreeViewDao tvdao;
        @Inject CustomerTypeDao csdao;
        List<Role> roles = new ArrayList<>();
        List<Role> rolesdeleted = new ArrayList<>();
        List<Role> rolesadded = new ArrayList<>();
        private FTwinColSelect<Role> tcsroles;
        private Button b1 = new Button("added");
        private Button b2 = new Button("deleted");
        public TestView(){
            
        }
        @PostConstruct
        public void init(){
        roles = roledao.findAll();
        List<TreeViewConfig> tvs = new ArrayList<>();
        List<CustomerType> css = new ArrayList<>();
        css=csdao.findAllWithTranslation();
        tcsroles = new FTwinColSelect<>(null,roles);
        tcsroles.setItemCaptionGenerator(e->((Role)e).getName());
        this.addComponents(tcsroles,b1,b2);
        List<Role> selected = new ArrayList<>();
        Role r = new Role();
        r.setId(1);
        r.setName("Osman");
        selected.add(r);
        tcsroles.setSelected(selected);
        b1.addClickListener(e->{
            Notification.show(tcsroles.getDeleted().toString());
        });
                b2.addClickListener(e->{
            Notification.show(tcsroles.getNewlyAdded().toString());
        });
     }
        //@Override
}
