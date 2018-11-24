/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "databasefile")
@NamedQuery(name="DatabaseFile.findAll", query="SELECT e FROM DatabaseFile e where 1=1")
public class DatabaseFile extends AbstractEntity {
    @Lob
    @Column(length=1000000)
    private byte[] content;
    private String filename;
    private String mimetype;
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name="filecontainerid",referencedColumnName = "id")
    private FileContainer filecontainer;

    public FileContainer getFilecontainer() {
        return filecontainer;
    }

    public DatabaseFile() {
    }

    public void setFilecontainer(FileContainer filecontainer) {
        this.filecontainer = filecontainer;
    }

    public DatabaseFile(FileContainer filecontainer , byte[] content, String filename, String mimetype) {
        this.content = content;
        this.filename = filename;
        this.mimetype = mimetype;
        this.filecontainer = filecontainer;
    }
    

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }
    
    
}
