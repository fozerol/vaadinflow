/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import com.mycompany.mavenproject3.entity.TreeViewConfig;
import com.mycompany.mavenproject3.appdao.TreeViewDao;
import com.vaadin.cdi.UIScoped;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
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
public   Class<? extends View>  initClassFilename(String className) throws NoSuchMethodException, InstantiationException, ClassNotFoundException
{
   Class<? extends View> panel = null;
        try {
            panel = (Class<? extends View>) Class.forName( "com.mycompany.mavenproject3.view."+className );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return panel;
}
//@PostConstruct
public void init() throws NoSuchMethodException, InstantiationException, ClassNotFoundException
{
    this.setWidth("250px");
    Tree<TreeViewConfig> tree = new Tree("Applications");
    TreeData<TreeViewConfig> td = new TreeData<>();
    treeviewconfigs = service.findAll();
    boolean br = false;
    for (TreeViewConfig t:treeviewconfigs){
            for (TreeViewConfig g:treeviewconfigs){
                if (g.getId()==t.getHierarchy() && g.getId()!=t.getId())
                {
                    td.addItem(g, t);
                    if (t.getClassFileName() != null)
                    br=true;
                break;
                }
                br=false;
            }
            if (!br){
                td.addItem(null,t);
                if (t.getClassFileName() != null)
                navigator.addView(t.getNodeName(), initClassFilename(t.getClassFileName()));
            }
         
    }
  //   td.addItems(null, service.findByNode(-1));
     tree.setDataProvider(new TreeDataProvider<>(td));
     tree.setItemCaptionGenerator(o -> o.getNodeCaption());
    
   // td.addItems(null, service.findByNode(-1));
    
    //treeviewconfigs = service.findAll();
    
    tree.addItemClickListener(e->{
        if (e.getItem().getClassFileName() != null){
        try {
            navigator.addView(e.getItem().getNodeName(), initClass(e.getItem().getClassFileName()));
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(TreeViewUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TreeViewUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreeViewUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        navigator.navigateTo(e.getItem().getNodeName());
        }
    });
    this.addComponent(tree);
    /*Button b = null;
    for (TreeViewConfig tc : treeviewconfigs)
    {
     navigator.addView(tc.getNodeName(), initClass(tc.getClassFileName()));
     b = new Button(tc.getNodeName());
     b.addClickListener(e->{
     navigator.navigateTo(tc.getNodeName());
     });
     this.addComponent(b);
    }*/
    /*navigator.addViewChangeListener(e->{
        View v = e.getOldView();
        e.getOldView().getClass();
        navigator.removeView();
        v = null;
        return true;
});*/
    }
}