/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.flow.Flow;
import com.mycompany.mavenproject3.helper.FlowForm;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author fatih
 */
//@CDIView("View2")
public class View2 extends HorizontalLayout implements View,FlowForm {

    @Override
    public void setFlow(Flow flow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Flow getFlow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadFlowFormData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public class Planet {
        private int id;
        private String name;
        public Planet (int id,String name)
        {
            this.setId(id);
            this.setName(name);
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
                
    }
    private TextField text1 = new TextField("Deneme");
    private ComboBox status1 = new ComboBox();
    List<Planet> planets = new ArrayList<>();
    public View2() {
        addComponents(text1,status1);
     planets.add(new Planet(1,"Mercur"));
     planets.add(new Planet(2,"Jupiter"));
     planets.add(new Planet(3,"Earth"));
    status1.setItems(planets);     
        //status1.addItems(CustomerStatus.values());
    }
    public View2(EntityManager em) {
        addComponents(text1,status1);
     planets.add(new Planet(1,"Mercur"));
     planets.add(new Planet(2,"Jupiter"));
     planets.add(new Planet(3,"Earth"));
    status1.setItems(planets);     
        //status1.addItems(CustomerStatus.values());
    }
    
    

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
