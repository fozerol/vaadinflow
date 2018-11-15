/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import static com.mycompany.mavenproject3.UserService.hasRole;
import com.mycompany.mavenproject3.entity.TreeViewConfig;
import com.mycompany.mavenproject3.appdao.TreeViewDao;
import com.mycompany.mavenproject3.entity.auth.AppRole;
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

    /*Populating Tree Components content looping in config list check one related to other hiyerarhy with id when config is root catched this exeption with isroot 
    variable then added below
    nagivator adding and nagivating done dynamicly in Tree item click listener
    Navigator can work 2 mode one of with initilaized  class which is not destroyed when navigatod other mode with Class<T> which inits the class and destroys it with navigation
    class with init done by initClass Method and Class<T> obtain by initClassFilename Method
    if you want to change behavior thats up to you
    */


/**
 *
 * @author fatih
 */
@UIScoped
public class TreeViewUI_1 extends VerticalLayout {
@Inject
@Any
Instance<Object> myBeans;
    
    @Inject
    TreeViewDao service;
    @Inject EntityManager em;
    Navigator navigator;
    private List<TreeViewConfig> treeviewconfigs = new ArrayList<>();

public TreeViewUI_1()
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
           // panel = Class.forName( "com.mycompany.mavenproject3.view."+className );
            panel = Class.forName( className );
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
    boolean isroot = false;
    for (TreeViewConfig t:treeviewconfigs){
            for (TreeViewConfig g:treeviewconfigs){
                if (g.getId()==t.getHierarchy() && g.getId()!=t.getId() && checkRole(t.getAppRoles()) && checkRole(g.getAppRoles()))
                {
                    td.addItem(g, t);
                    if (t.getClassFileName() != null)
                        isroot=true;
                break;
                }
                isroot=false;
            }
            if (!isroot && t.getHierarchy()==-1 && checkRole(t.getAppRoles())){
                td.addItem(null,t);
                //if (t.getClassFileName() != null)
                //navigator.addView(t.getNodeName(), initClassFilename(t.getClassFileName()));
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
    this.addStyleName("valo-menu");
    
    }
    protected boolean checkRole(List<AppRole> approles ){
        for (AppRole ar:approles){
            if (hasRole(ar.getRole().getName()))
                    return true;
       }
    return false;
    }
}