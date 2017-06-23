/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.flow;

import com.mycompany.mavenproject3.ClassFactory;
import com.mycompany.mavenproject3.appdao.FlowDao;
import com.mycompany.mavenproject3.helper.FlowForm;
import com.vaadin.cdi.UIScoped;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author fatih
 */
public class FlowFormView extends VerticalLayout implements View{
    @Inject FlowDao flowdao;
    @Inject Flow flow;
    @Inject
    @Any
    Instance<Object> myBeans;
    Navigator navigator;
    List<Flow> flows = null;

    private Grid<Flow> grid = new Grid();
    public FlowFormView()
    {
        
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
    
    @PostConstruct
    public void init(){
        flows = flowdao.findAll();
    grid.setItems(flowdao.findAll());
    grid.addColumn(Flow::getSenderId).setCaption("Sender");
    grid.addColumn(Flow::getStarterId).setCaption("Starter");
    grid.addColumn(Flow::getReceiverId).setCaption("Receiver");
    
    
    this.addComponents(grid);
    grid.addItemClickListener(e->{
        View v = null;
        try {
            
            v = ClassFactory.getClass(e.getItem().getFlowform(), myBeans);
            navigator.addView("tmp", v);
            ((FlowForm)v).setFlow(e.getItem());
            ((FlowForm)v).loadFlowFormData();
            
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FlowFormView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FlowFormView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FlowFormView.class.getName()).log(Level.SEVERE, null, ex);
        }
        navigator.navigateTo("tmp");
    });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }


}
