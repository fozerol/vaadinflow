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
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import javax.persistence.EntityManager;

/**
 *
 * @author fatih
 */
//@CDIView("View1")
public class View1 extends VerticalLayout implements View ,FlowForm{
    private final String NAME ="view1";
    private TextField text1 = new TextField();
    private PasswordField pass1 = new PasswordField();
    
    public View1()
    {
               addComponents(text1,pass1);
    }
    public View1(EntityManager em)
    {
               addComponents(text1,pass1);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
    
}
