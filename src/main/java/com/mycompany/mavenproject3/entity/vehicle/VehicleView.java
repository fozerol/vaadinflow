/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity.vehicle;

//import com.mycompany.mavenproject3.DatabaseFile;
import com.mycompany.mavenproject3.FileUploading;
import com.mycompany.mavenproject3.entity.DatabaseFile;
import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.UserDownladFile;
//import com.mycompany.mavenproject3.UserDownladFile;
import com.mycompany.mavenproject3.appdao.CustomerDao;
import com.mycompany.mavenproject3.customcomponent.FileUpload;
import com.mycompany.mavenproject3.entity.Customer;
import com.mycompany.mavenproject3.entity.FileContainer;
import com.mycompany.mavenproject3.view.GenericViewV2;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Image;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import genericdao.GenericDaoImp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.appdao.CustomerDao;
import com.mycompany.mavenproject3.entity.Customer;
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

public class VehicleView extends GenericViewV2<Vehicle>  {


    @Inject GenericDaoImp<Vehicle> dao;
    @Inject GenericDaoImp<VehicleType> typedao;
    @Inject Vehicle vehicle;
    @Inject CustomerDao customerdao;
    private TextField plateNumber = new TextField(getText("PLATE_NUMBER"));
    private DateField firstRegDate = new DateField(getText("FIRST_REG_DATE"));
    private TextField vin = new TextField(getText("VIN"));
    private TextField shortvin = new TextField(getText("SHORT_VIN"));
    private ComboBox<VehicleType> type = new ComboBox<>(getText("VEHICLE_TYPE"));
    private ComboBox<Customer> customer = new ComboBox<>(getText("OWNER"));
    private Upload file;
    private GridCellFilter filter;
    private FileUpload fileupload;
    private Button fileops = new Button(getText("FILE_OPS"));
    ByteArrayOutputStream baos;

    public VehicleView() {
        
    }
    @PostConstruct
    public void init(){

        /*byte[] initialArray = { 0, 1, 2 };
        filedownload = new UserDownladFile(new DatabaseFile(initialArray,"a","a"));*/
        
        file = new Upload("UPLOAD_FILE",new Receiver() 
        {
    public File file;
    //ByteArrayOutputStream  bos = new ByteArrayOutputStream();
    
    
    @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
            baos = new ByteArrayOutputStream();
            return baos;
            };                
            
        });
        file.addFinishedListener(e-> {
            /*//byte[] p = e.getFilename().getBytes();
        byte[] p = baos.toByteArray();
        ((Vehicle)this.getObject()).setFilename(e.getFilename());
        ((Vehicle)this.getObject()).setFile(p);
        ((Vehicle)this.getObject()).setMimetype(e.getMIMEType());*/
        });

        this.setDao(dao);
        dao.setType(Vehicle.class);
        this.setObject(vehicle);
        typedao.setType(VehicleType.class);
        type.setItems(typedao.findAll());
        type.setItemCaptionGenerator(e-> e.getName());
        binder.bindInstanceFields(this);
        grid.setItems(dao.findAllByCompany());
        filter = new GridCellFilter(grid,Vehicle.class);
        customer.setItems(customerdao.findAllByCompany());
        customer.setItemCaptionGenerator(e-> e.getName());
        fileops.addClickListener(e->{
            fileupload = new FileUpload((FileUploading)getObject());
            this.getUI().addWindow(fileupload);
            
                fileupload.addCloseListener(b->{
            grid.clearSortOrder();
            });
        });
        
        //this.addComponents(plateNumber,firstRegDate,vin,shortvin,customer,type,buttons,fileops,grid);
        
        
        grid.addItemClickListener(e->{
             /*this.setObject(e.getItem());
             DatabaseFile df = new DatabaseFile(((Vehicle)this.getObject()).getFile(),((Vehicle)this.getObject()).getFilename(),((Vehicle)this.getObject()).getMimetype());
//             filedownload.setFile(df);
            if (filedownload != null) {
            this.removeComponent(filedownload);
            }
            ;*/
        });
        this.addComponents(plateNumber,firstRegDate,vin,shortvin,customer,type,buttons,fileops,grid);

    }
   
}
