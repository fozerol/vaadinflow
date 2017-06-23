/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import com.vaadin.navigator.View;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Instance;

/**
 *
 * @author fatih
 */
public class ClassFactory {
    public static View getClass(String className,Instance<Object> myBeans) throws NoSuchMethodException, InstantiationException, ClassNotFoundException
{
   Class<?> panel = null;
   View newScreen = null;
        try {
            panel = Class.forName( className );
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
    
}
