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
public abstract class AbstractFileUploadCompanyEntity extends  AbstractCompanyEntity implements FileUploading {
    
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
   
}