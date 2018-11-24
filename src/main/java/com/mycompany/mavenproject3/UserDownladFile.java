/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import com.mycompany.mavenproject3.entity.DatabaseFile;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 *
 * @author fatih
 */
public class UserDownladFile extends Button {
    private DatabaseFile file;
    public DatabaseFile getFile() {
        return file;
    }

    public void setFile(DatabaseFile file) {
        this.file = file;
        prepare();
    }

    public UserDownladFile(DatabaseFile file ) {
    this.file = file;
    prepare();
    }
    public void prepare(){
        this.setCaption("Download image");

        StreamResource myResource = createResource();
        FileDownloader fileDownloader = new FileDownloader(myResource);
        
        fileDownloader.extend(this);

    }
    
    
    private StreamResource createResource() {
        return new StreamResource(
                new StreamSource() {
            @Override
            public InputStream getStream() {
                String text = file.getFilename();
                InputStream ins;
                    ins = new ByteArrayInputStream(file.getContent());
                    return ins;
            }
        }, file.getFilename());
    }
}