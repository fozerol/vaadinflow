/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.appdao;

import com.vaadin.data.Binder;
import com.vaadin.ui.Grid;
import genericdao.GenericDaoImp;

/**
 *
 * @author fatih
 */
public interface  GenericObject {
    void setObject(Object o);
    Object getObject();
    Object getNewInstance();
    Class<?> getType();
    Binder getBinder();
    Grid getGrid();
    boolean isValid();
    void setValid(boolean b);
}
