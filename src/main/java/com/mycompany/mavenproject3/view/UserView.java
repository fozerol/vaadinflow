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
import com.mycompany.mavenproject3.entity.auth.Role;
import com.mycompany.mavenproject3.entity.auth.UserRole;
import com.mycompany.mavenproject3.flow.Flow;
import com.mycompany.mavenproject3.flow.FlowFormData;
import com.mycompany.mavenproject3.genericbutton.GenericButtonGroup;
import com.mycompany.mavenproject3.helper.FlowForm;
import com.vaadin.data.ValueProvider;
import com.vaadin.server.Setter;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.vaadin.gridutil.cell.GridCellFilter;

/**
 *
 * @author fatih
 */
public class UserView extends GenericView<User> implements FlowForm{
        @Inject User user;
        @Inject UserDao dao;
        @Inject RoleDao roledao;
        @Inject FlowDao flowdao;
        @Inject CompanyDao companydao;
        private TextField name = new TextField("Name");
        private TextField surName = new TextField("Sur Name");
        private TextField userName = new TextField("username");
        private TextField email = new TextField("email");
        private PasswordField password = new PasswordField("password");
        private List<UserRole> userroles = new ArrayList<>();
        private List<Role> roles = new ArrayList<>();
        private List<Role> usergrantedroles = new ArrayList<>();
        private List<Role> savedroles = new ArrayList<>();
        private ComboBox<Company> company = new ComboBox<>("Company");
        private FTwinColSelect<Role> tcsroles;
        private GenericButtonGroup<User> genericbuttongroup;
        private Button createflow = new Button("Create Flow");
        private Flow flow = new Flow();
        private FlowFormData flowformdata = new FlowFormData();
        private GridCellFilter filter;
        public UserView(){
            
        }
        @PostConstruct
        public void init(){
        name.setId("1");
        surName.setId("2");
        userName.setId("3");
        email.setId("4");
        company.setId("5");
        this.grid.setId("6");
        this.setObject(user);
        roles = roledao.findAll();
        tcsroles = new FTwinColSelect<>(null,roles);
        tcsroles.setId("7");
        tcsroles.setItemCaptionGenerator(e->((Role)e).getName());
        company.setItems(companydao.findAll());
        company.setItemCaptionGenerator(o->o.getName());

        this.grid.setItems( dao.findAll());
        filter = new GridCellFilter<>(grid,User.class);
        filter.setTextFilter("name", valid, valid);
        this.grid.setWidth("700");
        this.binder.forField(name).bind(User::getName,User::setName);
        this.binder.forField(surName).bind(User::getSurname,User::setSurname);
        this.binder.forField(userName).bind(User::getUserName,User::setUserName);
        this.binder.forField(email).bind(User::getEmail,User::setEmail);
        this.binder.forField(password).bind(User::getPassword,User::setPassword);
        this.binder.forField(password).bind(new ValueProvider<User,String>(){
            public String apply(User user) {
                return null;
            }
            
        },new Setter<User,String>(){
            public void accept(User user,String password) {
            user.setPassword(generatePassord(password));
            }
        });
        /*this.binder.bind(password,
                new ValueProvider<User, String>() {
    @Override
    public String apply(User user) {
      return null;
    }
  },
  new Setter<User, String>() {
    @Override
    public void accept(User user, String password) {
        
        user.setPassword(generatePassord(password));
    }
  }
        );*/
    
        
        //this.binder.forField(roles).bind(User::getUserRoles,User::setUserRoles);
        //this.binder.forField(company).bind(User::setCompany,User::getCompany);
        this.binder.forField(company);
        this.binder.bindInstanceFields( this );
        genericbuttongroup = new GenericButtonGroup<>(dao,this);
        this.grid.getColumn("company").setHidden(true);
        //this.grid.addColumn(e->e.getCompany().getName()).setCaption("Company");
        this.addComponents(name,surName,userName,password,email,company,tcsroles,genericbuttongroup,createflow,grid);
        grid.addItemClickListener(e->{
           this.setObject((User) e.getItem());
           setTcsValue(((User)e.getItem()).getUserRoles());
       });
        createflow.addClickListener(e->{
                  flow.setReceiverId("ugur.ersoy");
        flow.setSendDate(new Date());
        flow.setStarterId("fatih.ozerol");
        flow.setFlowform(this.getClass().toString().replace("class ",""));
        flow.setFlowFormData(ComponentFinder.getFlowFormDatas(this,flow));
        flowdao.create(flow);
        });
     }
        //@Override
        public void setTcsValue(List<UserRole> userroles)
        {
                savedroles.clear();
            for (UserRole ur:userroles){
                for (Role r:roles){
                    if (r.getName()==ur.getRole().getName())
                        savedroles.add(r);
                }
           }
            
            tcsroles.setValue(savedroles.stream().collect(Collectors.toSet()));
        }
        public List<Role> geturoles(User user){
            usergrantedroles.clear();
            for (UserRole ur:user.getUserRoles())
                usergrantedroles.add(ur.getRole());
            return usergrantedroles;
           //return new ArrayList<Role>();
        }
        public User getObject(){
            /* find newly added list object */
            /* if (((User)this.getT()).getUserRoles() == null ) {
                ((User)this.getT()).setUserRoles(new ArrayList<UserRole>());
            }*/
            
            
            List<Role> sourceroles = findDifference((List<Role>) (tcsroles.getValue()).stream().collect(Collectors.toList()),geturoles(((User)this.getT())));
            for (Role r:sourceroles){
                UserRole ur = new UserRole();
                ur.setUser((User)this.getT());
                ur.setRole(r);
               ((User)this.getT()).getUserRoles().add(ur);
            }
            /*remove removed list object*/
            sourceroles = findDifference(geturoles(((User)this.getT())), (List<Role>) (tcsroles.getValue()).stream().collect(Collectors.toList()));
             for (Role r:sourceroles){
                    ((User)this.getT()).removeUserRoleByRole(r);
            }
            return (User)this.getT();
    }
        public List<Role> findDifference(List<Role> list1,List<Role> list2){
            List<Role> difference = new ArrayList<>();
            boolean isExist = false;
            for(Role r1:list1){
                
                for (Role r2:list2){
                    if (r1.getId()==r2.getId()){
                        isExist = true;
                        break;
                    }
                    }
                if (!isExist){
                        difference.add(r1);
                }
                isExist = false;
            }
            return difference;
        }
    public String generatePassord(String password){
        DefaultHashService hashService = new DefaultHashService();
        DefaultPasswordService passwordService = new DefaultPasswordService();
        passwordService.setHashService(hashService);
        String encryptedPassword = passwordService.encryptPassword(password);
        return encryptedPassword;
    }
    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }
    public void loadFlowFormData() {
        int i = flow.getFlowFormData().size();
        loadFormData(this, flow.getFlowFormData());
}
}
