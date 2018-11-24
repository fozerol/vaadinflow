/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import com.mycompany.mavenproject3.FileUploading;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author fatih
 */
@MappedSuperclass
public abstract class AbstractFileUploadEntity implements Serializable, Cloneable,FileUploading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private int version;
    
    @OneToOne
    (cascade = CascadeType.ALL)
    @JoinColumn(name = "filecontainerid")
    private FileContainer filecontainer;

    public FileContainer getFilecontainer() {
        return filecontainer;
    }

    public void setFilecontainer(FileContainer filecontainer) {
        this.filecontainer = filecontainer;
    }
    
    
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(this.id == null) {
            return false;
        }

        if (obj instanceof AbstractFileUploadEntity && obj.getClass().equals(getClass())) {
            return this.id.equals(((AbstractFileUploadEntity) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
}