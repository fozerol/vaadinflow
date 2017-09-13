/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view.generalform;

import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.appdao.address.CityDao;
import com.mycompany.mavenproject3.appdao.address.CountryDao;
import com.mycompany.mavenproject3.entity.Address;
import com.mycompany.mavenproject3.entity.City;
import com.mycompany.mavenproject3.entity.Country;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author fatih
 */
public class AddressFormUI extends Window {
    @Inject CountryDao countrydao;
    @Inject CityDao citydao;
    private Address addressobject;
    private FormLayout form = new FormLayout();
    private TextField address = new TextField(getText("ADDRESS"));
    private ComboBox<Country> country = new ComboBox<>(getText("COUNTRIES"));
    private ComboBox<City> city = new ComboBox<>(getText("CITIES"));
    private TextField zipcode = new TextField(getText("ZIPCODE"));
    private Binder<Address> binder = new Binder<>(Address.class);
    private Button cancel = new Button(getText("CANCEL"));
    private Button ok = new Button(getText("OK"));
    public AddressFormUI(){
    }
    @PostConstruct
    public void init(){
                country.setItems(countrydao.findAll());
        country.setItemCaptionGenerator(e->e.getName());
        country.addValueChangeListener(e->{
            city.setItems(citydao.findByCountry((Country)e.getValue()));
        });
        city.setItemCaptionGenerator(e-> e.getName());
        
        binder.bindInstanceFields(this);
//binder.forField(city).bind(Address::getCity,Address::setCity);
//binder.forField(address).bind(Address::getAddress,Address::setAddress);
//binder.forField(zipcode).bind(Address::getZipcode,Address::setZipcode);

//binder.forField(country).bind(Address::getCountry,Address::setCountry);

        form.addComponents(address,zipcode,country,city,cancel,ok);
        this.setContent(form);
        cancel.addClickListener(e->{
            this.close();
        });
        ok.addClickListener(e->{
            //binder.setBean(addressobject);
                    try {
                        binder.writeBean(addressobject);
                    } catch (ValidationException ex) {
                        Logger.getLogger(AddressFormUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
            //((AddressFormCaller)this.getParent()).setAddress(addressobject);
            this.close();
        });
    }

    public Address getAddressobject() {
        return addressobject;
    }

    public void setAddressobject(Address addressobject) {
        this.addressobject = addressobject;
    }
}
