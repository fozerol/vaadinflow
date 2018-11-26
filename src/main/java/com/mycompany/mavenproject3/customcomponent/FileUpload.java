/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.customcomponent;

import com.mycompany.mavenproject3.FileUploading;
import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.UserDownladFile;
import com.mycompany.mavenproject3.entity.DatabaseFile;
import com.mycompany.mavenproject3.entity.FileContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.renderers.ButtonRenderer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author fatih
 */
public class FileUpload extends Window {
    private VerticalLayout  layout = new VerticalLayout();
    private DatabaseFile dbfile;
    private Grid<DatabaseFile> grid = new Grid<>(DatabaseFile.class);
    private Button closeBtn = new Button(getText("CLOSE"));
    private Button okBtn = new Button(getText("OK"));
    private HorizontalLayout btnLayout = new HorizontalLayout();
    //private Button downloadBtn = new Button(getText("DOWNLOAD"));
    private FileContainer filecontainer;
    private Upload file;
    private FileContainer tempfilecontainer;
    private FileUploading fileuploading;
    private UserDownladFile filedownload;
    
    
    
    ByteArrayOutputStream baos;

    
    public FileUpload(FileUploading fileuploading) {
        tempfilecontainer  = new FileContainer();
        this.fileuploading=fileuploading;
        if (fileuploading.getFileContainer() == null)
        {
            this.filecontainer = new FileContainer();
            fileuploading.setFileContainer(this.filecontainer);
        }
        else
        {
            this.filecontainer = fileuploading.getFileContainer();
            for (DatabaseFile dbfile:fileuploading.getFileContainer().getDatabasefile())
            {
              tempfilecontainer.getDatabasefile().add(dbfile);
            }
        }
        setGrid();
        setUploader();
        setLayout();
        setListeners();
    }

    private void setGrid() {
        grid.setSizeFull();
        grid.getColumn("filecontainer").setHidden(true);
        grid.getColumn("content").setHidden(true);
        grid.getColumn("id").setHidden(true);
        
        grid.setItems(this.tempfilecontainer.getDatabasefile());
        grid.addColumn(e -> "Delete",
      new ButtonRenderer(clickEvent -> {
          this.tempfilecontainer.getDatabasefile().remove(clickEvent.getItem());
          grid.setItems(this.tempfilecontainer.getDatabasefile());

    }));
        
          
          grid.addItemClickListener( p->{
              if (filedownload != null)
              {
                    btnLayout.removeComponent(filedownload);
              }
            this.dbfile = p.getItem();
            filedownload = new UserDownladFile(new DatabaseFile(this.tempfilecontainer,dbfile.getContent(),dbfile.getFilename(),dbfile.getMimetype()));
            btnLayout.addComponent(filedownload);
              
          });        
        
    }

    private void setLayout() {
        btnLayout.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
        btnLayout.addComponents(file,closeBtn,okBtn);
        layout.addComponents(grid,btnLayout);
        
        this.center();
        this.setWidth("700px");
        this.setHeight("700px");
//        layout.setWidth("500px");
        this.setResizable(true);
       this.setContent(layout);
       this.setModal(true);
    }

    private void setListeners() {
        closeBtn.addClickListener(e->{
           this.close();
           });
       okBtn.addClickListener(e->{
           filecontainer.getDatabasefile().clear();
           for (DatabaseFile dbfile: this.tempfilecontainer.getDatabasefile())
           {
           filecontainer.getDatabasefile().add(dbfile);
           }
           this.close();
       });
    }

    private void setUploader() {
        file = new Upload("",new Upload.Receiver() 
        {
        public File file;
        @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
            baos = new ByteArrayOutputStream();
            return baos;
            };                
            
        });
        
       file.addFinishedListener(e-> {
        byte[] filecontent = baos.toByteArray();
        DatabaseFile dbfile = new DatabaseFile(this.filecontainer,filecontent , e.getFilename(),e.getMIMEType());
        this.tempfilecontainer.getDatabasefile().add(dbfile);
        grid.setItems(this.tempfilecontainer.getDatabasefile());
        });
    }
}
