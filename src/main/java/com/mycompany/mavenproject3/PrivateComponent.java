/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import com.mycompany.mavenproject3.appdao.FlowDao;
import com.mycompany.mavenproject3.flow.FlowFormView;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author fatih
 */
@CDIUI("homescreen")
@SuppressWarnings("serial")
public class PrivateComponent extends VerticalLayout {
    
    @Inject    TreeViewUI tvui;
    @Inject
    private CDIViewProvider viewProvider;
    @Inject FlowDao flowdao;
    @Inject FlowFormView flowformview;

    Navigator n;
    HorizontalLayout headerbar = new HorizontalLayout();
    HorizontalLayout midcontent = new HorizontalLayout();
    VerticalLayout content = new VerticalLayout();
    
    public PrivateComponent()
    {
        
        
        }
    public void initMenu()
    {   
        content.setWidth("1200");
        Button buttonlogout = new Button("Sign out", this::onLogout);
        Button myFlows = new Button("you have Flows:"+  Long.toString(flowdao.countAll()));
        midcontent.addComponents(tvui,content);
        n = new Navigator(MyUI.getCurrent(),content);
        headerbar.addComponents(buttonlogout,myFlows);
        addComponents(headerbar,midcontent);
        myFlows.addClickListener(e->{
            n.navigateTo("flows");
        });
        
        tvui.setNaviagator(n);
         n.addView("", WellCome.class);
         flowformview.setNavigator(n);
         n.addView("flows", flowformview);
        try {
            try {
                tvui.init();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PrivateComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    n.navigateTo("");
    }
    public void onLogout(Button.ClickEvent e){
        AuthService.logOut();
    }
}
