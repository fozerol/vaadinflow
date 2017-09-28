/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.view;

import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.appdao.CompanyDao;
import com.mycompany.mavenproject3.entity.Address;
import com.mycompany.mavenproject3.entity.Company;
import com.mycompany.mavenproject3.entity.Customer;
import com.mycompany.mavenproject3.flow.Flow;
import com.mycompany.mavenproject3.genericbutton.GenericButtonGroup;
import com.mycompany.mavenproject3.helper.FlowForm;
import com.mycompany.mavenproject3.view.generalform.AddressFormUI;
import com.vaadin.data.TreeData;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import java.text.NumberFormat;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.melodion.Melodion;
import org.vaadin.melodion.Melodion.Tab;

/**
 *
 * @author fatih
 */
public class CompanyView extends GenericView<Company> implements FlowForm{
        @Inject Company company;
        @Inject CompanyDao dao;
        @Inject AddressFormUI addressform;
        private TextField name = new TextField("Company name");
        private TextField taxNumber = new TextField("Tax Number");
        private TextField address = new TextField("Company adress");
        private Button addressbtn = new Button(getText("ADDRESS"));
        private GenericButtonGroup<Company> genericbuttongroup;
        public CompanyView(){
            
        }
        @PostConstruct
        public void init(){
        this.setObject(company);
        this.grid.setItems( dao.findAll());
        this.binder.forField(name).bind(Company::getName,Company::setName);
        this.binder.forField(address).bind(Company::getAddress,Company::setAddress);
        StringToLongConverter s = new StringToLongConverter("Must be valid tax number") {
        protected java.text.NumberFormat getFormat(Locale locale) {
        NumberFormat format = super.getFormat(locale);
        format.setGroupingUsed(false);
        return format;
        };
        };
        this.binder.forField(taxNumber).withNullRepresentation("").withConverter(s).withValidator(tax -> tax >= 10000000000L && tax < 99999999999L, "Person must have a valid tax number")
        .bind(Company::getTaxNumber,Company::setTaxNumber);
        this.binder.bindInstanceFields( this );
        genericbuttongroup = new GenericButtonGroup<>(dao,this);
       this.addComponents(name,taxNumber,address,addressbtn,genericbuttongroup,grid);
       grid.addItemClickListener(e->{
           this.setObject((Company) e.getItem());
       });
       addressform.setModal(true);
        addressbtn.addClickListener(e->{
            addressform.setAddresses(((Company)this.getObject()).getAddresses());
            this.getUI().addWindow(addressform);
        });
        addressform.addCloseListener(e->{
                for (Address address:((Company)this.getObject()).getAddresses() ){
                address.setCompany((Company)this.getObject());
            }
        });
        }

    @Override
    public void setFlow(Flow flow) {
        
    }

    @Override
    public Flow getFlow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadFlowFormData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
