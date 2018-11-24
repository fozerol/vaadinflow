/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.customcomponent;

import com.mycompany.mavenproject3.FileUploading;
import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.entity.DatabaseFile;
import com.mycompany.mavenproject3.entity.FileContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.MultiSelectionModel;
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
    private List<DatabaseFile> dbfiles;
    private Grid<DatabaseFile> grid = new Grid<>(DatabaseFile.class);
    private Button closeBtn = new Button(getText("CLOSE"));
    private Button okBtn = new Button(getText("OK"));
    private FileContainer filecontainer;
    private Upload file;
    private FileContainer tempfilecontainer;
    private FileUploading fileuploading;
    
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
            //tempfilecontainer.setDatabasefile(fileuploading.getFileContainer().getDatabasefile());
        }
        
        
        
        
        
        grid.setItems(this.tempfilecontainer.getDatabasefile());
     //   MultiSelectionModel<DatabaseFile> selectionModel = (MultiSelectionModel<DatabaseFile>) grid.setSelectionMode(SelectionMode.MULTI);
       file = new Upload("UPLOAD_FILE",new Upload.Receiver() 
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
        byte[] filecontent = baos.toByteArray();
        DatabaseFile dbfile = new DatabaseFile(this.filecontainer,filecontent , e.getFilename(),e.getMIMEType());
        this.tempfilecontainer.getDatabasefile().add(dbfile);
        grid.setItems(this.tempfilecontainer.getDatabasefile());
        });
       closeBtn.addClickListener(e->{
           this.close();
           
       });
       okBtn.addClickListener(e->{
           filecontainer.getDatabasefile().clear();
           for (DatabaseFile dbfile: this.tempfilecontainer.getDatabasefile())
           {
           filecontainer.getDatabasefile().add(dbfile);
           }
           
           //fileuploading.setFileContainer(this.tempfilecontainer);
           //filecontainer.setDatabasefile(this.tempfilecontainer.getDatabasefile());
           this.close();
       
       });
       layout.addComponents(grid,file,closeBtn,okBtn);
        this.center();
        layout.setWidth("500px");
        this.setResizable(true);
       this.setContent(layout);
       this.setModal(true);
    }
}
