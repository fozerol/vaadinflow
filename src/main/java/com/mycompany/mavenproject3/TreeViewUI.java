/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import com.mycompany.mavenproject3.entity.TreeViewConfig;
import com.mycompany.mavenproject3.appdao.TreeViewDao;
import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author fatih
 */
@UIScoped
public class TreeViewUI extends VerticalLayout {
@Inject
@Any
Instance<Object> myBeans;
    
    @Inject
    TreeViewDao service;
    @Inject EntityManager em;
    Navigator navigator;
    private List<TreeViewConfig> treeviewconfigs = new ArrayList<>();

public TreeViewUI()
{

}
public Navigator getNaviagor() {
        return navigator;
    }

    public void setNaviagator(Navigator n) {
        this.navigator = n;
    }
public View initClass(String className) throws NoSuchMethodException, InstantiationException, ClassNotFoundException
{
   Class<?> panel = null;
   View newScreen = null;
//    Constructor c = null;
//            panel = Class.forName( "com.mycompany.mavenproject3."+className );

        try {
            panel = Class.forName( "com.mycompany.mavenproject3.view."+className );
  //          c = Class.forName( "com.mycompany.mavenproject3."+className ).getConstructor(EntityManager.class);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //newScreen = (View) c.newInstance(em);
            newScreen =  (View)myBeans.select(panel).get();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeViewUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newScreen;
}
//@PostConstruct
public void init() throws NoSuchMethodException, InstantiationException, ClassNotFoundException
{
    treeviewconfigs = service.findAll();
    Button b = null;
    for (TreeViewConfig tc : treeviewconfigs)
    {
     navigator.addView(tc.getNodeName(), initClass(tc.getClassFileName()));
     b = new Button(tc.getNodeName());
     b.addClickListener(e->{
     navigator.navigateTo(tc.getNodeName());
     });
     this.addComponent(b);
    }
}
}