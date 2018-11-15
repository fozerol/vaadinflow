/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.AuthService;
import com.mycompany.mavenproject3.MyUI;
import com.mycompany.mavenproject3.TranslationSvc;
import static com.mycompany.mavenproject3.TranslationSvc.getText;
import static com.mycompany.mavenproject3.UserService.getLastTime;
import com.mycompany.mavenproject3.VaadinSecurityContext;
import com.mycompany.mavenproject3.VaadinSessionManager;
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
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import genericdao.GenericDaoImp;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
        //@Inject GenericDaoImp<City> citydao;
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
        //citydao.setType(City.class);
        roles = roledao.findAll();
        //city.setItems(citydao.findAll());
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
            pdftest();
            //return;            VaadinSecurityContext c = new VaadinSecurityContext();
            //Notification.show(AuthService.getVaadinSession().getAttribute("SESSION_USERNAME", username)));
          //Notification.show(c.getSubject().getSession().getLastAccessTime()                      + getText(" BLABLA")+ AuthService.getUser().getName()+AuthService.getLanguage().getName());
        Notification.show( getLastTime()               + getText(" BLABLA")+ AuthService.getUser().getName()+AuthService.getLanguage().getName());              

              

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

    private void pdftest() {
Window window = new Window();
//((VerticalLayout) window.getContent()).setSizeFull();
window.setResizable(true);
window.setCaption("Exemplo PDF");
window.setWidth("800");
window.setHeight("600");
window.center();
StreamSource s = new StreamResource.StreamSource() {

@Override
public InputStream getStream() {
try {
File f = new File("/home/fatih/DocxResume.pdf");
FileInputStream fis = new FileInputStream(f);
return fis;
} catch (Exception e) {
e.printStackTrace();
return null;
}
}
};

StreamResource r = new StreamResource(s, "repy.pdf");
Embedded e = new Embedded();
e.setSizeFull();
e.setType(Embedded.TYPE_BROWSER);
r.setMIMEType("application/pdf");

e.setSource(r);
window.setContent(e);
this.getUI().addWindow(window);
    }
}
