/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.ComponentFinder;
import static com.mycompany.mavenproject3.ComponentFinder.loadFormData;
import com.mycompany.mavenproject3.appdao.CompanyDao;
import com.mycompany.mavenproject3.appdao.FlowDao;
import com.mycompany.mavenproject3.appdao.UserDao;
import com.mycompany.mavenproject3.appdao.auth.RoleDao;
import com.mycompany.mavenproject3.customcomponent.FTwinColSelect;
import com.mycompany.mavenproject3.entity.auth.User;
import com.mycompany.mavenproject3.entity.Company;
import com.mycompany.mavenproject3.entity.EntityObject;
import com.mycompany.mavenproject3.entity.auth.Role;
import com.mycompany.mavenproject3.entity.auth.UserRole;
import com.mycompany.mavenproject3.flow.Flow;
import com.mycompany.mavenproject3.flow.FlowFormData;
import com.mycompany.mavenproject3.genericbutton.GenericButtonGroup;
import com.mycompany.mavenproject3.helper.FlowForm;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author fatih
 */
public class TestView extends GenericView<User>{
        @Inject RoleDao roledao;
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
