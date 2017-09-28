/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view.generalform;

import static com.mycompany.mavenproject3.TranslationSvc.getText;
//import com.mycompany.mavenproject3.appdao.address.CityDao;
import com.mycompany.mavenproject3.appdao.address.CountryDao;
import com.mycompany.mavenproject3.entity.Address;
import com.mycompany.mavenproject3.entity.City;
import com.mycompany.mavenproject3.entity.Country;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import genericdao.GenericDaoImp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author fatih
 */
public class AddressFormUI extends Window {
    @Inject CountryDao countrydao;
    //@Inject CityDao citydao;
    @Inject GenericDaoImp<City> citydao;
    private List<Address> addresses = new ArrayList<>();
    private List<Address> senderformaddresses;
    private Address addressobject;
    private FormLayout form = new FormLayout();
    private TextField address = new TextField(getText("ADDRESS"));
    private ComboBox<Country> country = new ComboBox<>(getText("COUNTRIES"));
    private ComboBox<City> city = new ComboBox<>(getText("CITIES"));
    private TextField zipcode = new TextField(getText("ZIPCODE"));
    private Binder<Address> binder = new Binder<>(Address.class);
    private Button cancelBtn = new Button(getText("CANCEL"));
    private Button addBtn = new Button(getText("ADD"));
    private Button okBtn = new Button(getText("OK"));
    private Button deleteBtn = new Button(getText("DELETE"));
    private Button updateBtn = new Button(getText("UPDATE"));
    private Grid<Address> grid;

    public AddressFormUI(){
    }
    @PostConstruct
    public void init(){
        this.center();
        form.setWidth("500px");
        this.setResizable(true);
        gridinit();
        country.setItems(countrydao.findAll());
        country.setItemCaptionGenerator(e->e.getName());
        country.addValueChangeListener(e->{
            //city.setItems(citydao.findByCountry((Country)e.getValue()));
            city.setItems(citydao.findAll());
        });
        city.setItemCaptionGenerator(e-> e.getName());
        
        binder.bindInstanceFields(this);
//binder.forField(city).bind(Address::getCity,Address::setCity);
//binder.forField(address).bind(Address::getAddress,Address::setAddress);
//binder.forField(zipcode).bind(Address::getZipcode,Address::setZipcode);

//binder.forField(country).bind(Address::getCountry,Address::setCountry);

        form.addComponents(address,zipcode,country,city,cancelBtn,okBtn,addBtn,deleteBtn,updateBtn,grid);
        this.setContent(form);
        updateBtn.addClickListener(e->{
            try {
                binder.writeBean(addressobject);
            } catch (ValidationException ex) {
                Logger.getLogger(AddressFormUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            grid.setItems(addresses);
        });
        deleteBtn.addClickListener(e->{
        addresses.remove(addressobject);
            grid.setItems(addresses);
            binder.readBean(new Address());
    });
        cancelBtn.addClickListener(e->{
            this.close();
        });
        okBtn.addClickListener(e->{
         senderformaddresses.clear();
         //Collections.copy(senderformaddresses,addresses);
//         senderformaddresses = (List<Address>) ((ArrayList)addresses).clone();
           //senderformaddresses = addresses.stream().collect(toList());
           for (Address adress:addresses){
               senderformaddresses.add(adress);
           }
         this.close();
        });
        addBtn.addClickListener(e->{
            try {
                addressobject = new Address();
                binder.writeBean(addressobject);
            } catch (ValidationException ex) {
                Logger.getLogger(AddressFormUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            addresses.add(addressobject);
            //grid.getDataProvider().refreshAll();
            grid.setItems(addresses);
            binder.readBean(new Address());
        });
    }
    

    

    private void gridinit() {
            grid = new Grid<>(Address.class);
            grid.setItems(addresses);
            grid.getColumn("city").setHidden(true);
            grid.addColumn(e-> e.getCity().getName());
            grid.addItemClickListener(e->{
                addressobject = e.getItem();
                binder.readBean(addressobject);
            });
    }
    
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        //this.addresses = (List<Address>) ((ArrayList)addresses).clone();
        //Collections.copy(addresses, this.addresses);
        //this.addresses = addresses.stream().collect(toList());
        this.addresses.clear();
        for (Address address:addresses){
            this.addresses.add(address);
        }
        this.senderformaddresses = addresses;
        grid.setItems(this.addresses);
    }

}
