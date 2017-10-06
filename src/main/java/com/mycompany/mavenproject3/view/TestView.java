/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.AuthService;
import com.mycompany.mavenproject3.TranslationSvc;
import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.appdao.CustomerTypeDao;
import com.mycompany.mavenproject3.appdao.TreeViewDao;
import com.mycompany.mavenproject3.appdao.auth.RoleDao;
import com.mycompany.mavenproject3.customcomponent.FTwinColSelect;
import com.mycompany.mavenproject3.entity.City;
import com.mycompany.mavenproject3.entity.Country;
import com.mycompany.mavenproject3.entity.CustomerType;
import com.mycompany.mavenproject3.entity.TreeViewConfig;
import com.mycompany.mavenproject3.entity.auth.User;
import com.mycompany.mavenproject3.entity.auth.Role;
import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import genericdao.GenericDaoImp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author fatih
 */

//@UIScoped
public class TestView extends GenericView<User>{
        @Inject RoleDao roledao;
        @Inject TreeViewDao tvdao;
        //@Inject CustomerTypeDao csdao;
        @Inject GenericDaoImp<City> citydao;
        List<Role> roles = new ArrayList<>();
        List<Role> rolesdeleted = new ArrayList<>();
        List<Role> rolesadded = new ArrayList<>();
        private FTwinColSelect<Role> tcsroles;
        private ComboBox<City> city = new ComboBox<>("city");
        private Button b1 = new Button(TranslationSvc.getText("SAVE"));
        private Button b2 = new Button(TranslationSvc.getText("DELETE"));
        public TestView(){
            
        }
        @PostConstruct
        public void init(){
        citydao.setType(City.class);
        roles = roledao.findAll();
        city.setItems(citydao.findAll());
        List<TreeViewConfig> tvs = new ArrayList<>();
        List<CustomerType> css = new ArrayList<>();
        //css=csdao.findAllWithTranslation();
        tcsroles = new FTwinColSelect<>(null,roles);
        tcsroles.setItemCaptionGenerator(e->((Role)e).getName());
        this.addComponents(tcsroles,city,b1,b2);
        List<Role> selected = new ArrayList<>();
        Role r = new Role();
        r.setId(1);
        r.setName("Osman");
        selected.add(r);
        

        tcsroles.setSelected(selected);
        b1.addClickListener(e->{
            //Notification.show(AuthService.getVaadinSession().getAttribute("SESSION_USERNAME", username)));
              Notification.show(getText("BLABLA")+ AuthService.getUsername()+AuthService.getLanguage().getName());
        City c = new City();
        Country p = new Country();
        p.setCode("DE");
        p.setName("Germany");
        c.setCountry(p);
        c.setName("Duseldorf");
        citydao.create(c);

              

            //Notification.show(tcsroles.getDeleted().toString());
            /*String s = "";
            for (Role role : roles){
            if (subject.hasRole(role.getName()))
                    {
                        s += "has role"+role.getName();
                    }
            else 
            {
                s += "has Not role"+role.getName();
            }
            
        }*/
           // Notification.show(s);
//System.out.print(encryptedPassword);
        });
                b2.addClickListener(e->{
            Notification.show(tcsroles.getNewlyAdded().toString());
        });
     }
        //@Override
}
