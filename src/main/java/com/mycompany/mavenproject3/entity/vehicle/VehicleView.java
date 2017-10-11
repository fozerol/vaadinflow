/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity.vehicle;

import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.view.GenericViewV2;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import genericdao.GenericDaoImp;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.gridutil.cell.GridCellFilter;

/**
 *
 * @author fatih
 */
public class VehicleView extends GenericViewV2<Vehicle> {
    @Inject GenericDaoImp<Vehicle> dao;
    @Inject GenericDaoImp<VehicleType> typedao;
    @Inject Vehicle vehicle;
    private TextField plateNumber = new TextField(getText("PLATE_NUMBER"));
    private DateField firstRegDate = new DateField(getText("FIRST_REG_DATE"));
    private TextField vin = new TextField(getText("VIN"));
    private TextField shortvin = new TextField(getText("SHORT_VIN"));
    private ComboBox<VehicleType> type = new ComboBox<>(getText("VEHICLE_TYPE"));
    private GridCellFilter filter;

    public VehicleView() {
        
    }
    @PostConstruct
    public void init(){
        this.setDao(dao);
        dao.setType(Vehicle.class);
        this.setObject(vehicle);
        typedao.setType(VehicleType.class);
        type.setItems(typedao.findAll());
        type.setItemCaptionGenerator(e-> e.getName());
        binder.bindInstanceFields(this);
        grid.setItems(dao.findAllByCompany());
        filter = new GridCellFilter(grid,Vehicle.class);
        this.addComponents(plateNumber,firstRegDate,vin,shortvin,type,buttons,grid);
    }
    
}
