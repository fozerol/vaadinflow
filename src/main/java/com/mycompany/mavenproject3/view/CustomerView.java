/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import com.mycompany.mavenproject3.ComponentFinder;
import static com.mycompany.mavenproject3.ComponentFinder.loadFormData;
import com.mycompany.mavenproject3.appdao.CustomerDao;
import com.mycompany.mavenproject3.appdao.CustomerTypeDao;
import com.mycompany.mavenproject3.appdao.FlowDao;
import com.mycompany.mavenproject3.appdao.FlowFormDataDao;
import com.mycompany.mavenproject3.entity.Customer;
import com.mycompany.mavenproject3.entity.CustomerType;
import com.mycompany.mavenproject3.flow.Flow;
import com.mycompany.mavenproject3.flow.FlowFormData;
import com.mycompany.mavenproject3.helper.FlowForm;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.provider.DataCommunicator;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.grid.MGrid;



/**
 *
 * @author fatih
 */
public class CustomerView extends VerticalLayout implements View,FlowForm {
    @Inject CustomerDao dao;
    @Inject Customer customer;
    @Inject CustomerType customertype;
    @Inject CustomerTypeDao typedao;
    @Inject FlowDao flowdao;
    //@Inject FlowFormDataDao flowformdatadao;
    private TextField name = new TextField("Name");
    private TextField surname = new TextField("Surname");;
    private TextField taxnumber = new TextField("Tax Number");
    private ComboBox<CustomerType>  type =new ComboBox<>("Select Type");
    private Button send = new Button("Send to Flow");
    private Grid grid = new Grid<>(Customer.class);
    private List<Customer> customers = new ArrayList<>();
    private Button saveBtn=new Button("Save");
    private Button gridtest = new Button("gridtest");
    private Binder<Customer> binder = new Binder<>();
    private Flow flow = new Flow();
    private List<Object> l = new ArrayList<>();
    public CustomerView(){
        name.setId("tf1");
        surname.setId("tf2");
        taxnumber.setId("3");
        type.setId("combo1");
        send.setId("Button1");
        grid.setId("grid1");
        saveBtn.setId("save1");
        this.addComponents(send,saveBtn,name,surname,type,taxnumber);
        CssLayout c = new CssLayout();
        this.setMargin(true);
        this.setSpacing(true);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }
    @PostConstruct
    public void init(){
        
        initBinder();
        type.setItems(typedao.findAll());
        type.setItemCaptionGenerator(CustomerType::getName);
        initGrid();
        grid.setItems(dao.findAll());
        this.addComponents(grid);
        
        saveBtn.addClickListener(e-> {
            try {
                binder.writeBean(customer);
            } catch (ValidationException ex) {
                Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
            }
                dao.create(customer);
                grid.setItems(dao.findAll());
        });
        send.addClickListener(e->{
        //ComponentFinder.findAllComponents(this);
//        flowformdatadao.create(ComponentFinder.getFlowFormDatas(this));
        
        flow.setFlowform("customer");
        flow.setReceiverId("ugur.ersoy");
        flow.setSendDate(new Date());
        flow.setStarterId("fatih.ozerol");
        flow.setFlowform(this.getClass().toString().replace("class ",""));
        flow.setFlowFormData(ComponentFinder.getFlowFormDatas(this,flow));
        flowdao.create(flow);
        });
    }
    public void initGrid(){
                 grid.addColumn(e -> {
             if (((Customer)e).getType() == null)
             {
                 return "null";
             }
             return ((Customer)e).getType().getName();
         });
         grid.getColumn("type").setHidden(true);
         //()grid.getDataProvider()
    }
    public void initBinder()
    {
                
      binder.forField(taxnumber).withConverter(
    new StringToIntegerConverter("Must enter a number")).
              bind(Customer::getTaxnumber,Customer::setTaxnumber);
      binder.forField(name).bind(Customer::getName,Customer::setName);
      binder.forField(surname).bind(Customer::getSurname,Customer::setSurname);
      binder.forField(type).bind(Customer::getType,Customer::setType);  
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
