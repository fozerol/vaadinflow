/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "filecontainers")
@NamedQuery(name="FileContainer.findAll", query="SELECT e FROM FileContainer e where 1=1")
public class FileContainer extends AbstractEntity {

    public FileContainer() {
    }
            
    @OneToMany
    (mappedBy = "filecontainer",orphanRemoval=true, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DatabaseFile> databasefile = new ArrayList<>();

    public List<DatabaseFile> getDatabasefile() {
        return databasefile;
    }

    public void setDatabasefile(List<DatabaseFile> databasefile) {
        this.databasefile = databasefile;
    }
    
    
}
