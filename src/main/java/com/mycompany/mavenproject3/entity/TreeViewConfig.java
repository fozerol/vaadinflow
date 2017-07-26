/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import com.mycompany.mavenproject3.entity.auth.AppRole;
import com.mycompany.mavenproject3.entity.auth.Role;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "treeviewconfig")
@NamedQueries({
@NamedQuery(name="TreeViewConfig.findAll", query="SELECT e FROM TreeViewConfig e order by e.position"),
@NamedQuery(name="TreeViewConfig.findByNode", query="SELECT e FROM TreeViewConfig e where e.hierarchy=:hierarchy"),
@NamedQuery(name="TreeViewConfig.findById", query="SELECT e FROM TreeViewConfig e where e.id=:id")
})
@NamedNativeQuery(name="TreeViewConfig.findByUser",query = "select t.* from treeviewconfig t,roles r,users u where u.username= ? and u.roleid= ")



public class TreeViewConfig implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String nodeName;
private String classFileName;
private int position;
private int hierarchy;
private String nodeCaption;
@OneToMany
(mappedBy = "treeviewconfig", cascade = CascadeType.ALL)
private List<AppRole> appRoles;
    public TreeViewConfig () {
        
    }
            
    public TreeViewConfig(int id, String nodeName, String classFileName, int position ,int hierarchy) {
        this.id = id;
        this.nodeName = nodeName;
        this.classFileName = classFileName;
        this.hierarchy = hierarchy;
        this.position = position;
    }

    public void setAppRoles(List<AppRole> appRoles) {
        this.appRoles = appRoles;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getClassFileName() {
        return classFileName;
    }

    public void setClassFileName(String classFileName) {
        this.classFileName = classFileName;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int hierarchy) {
        this.position = hierarchy;
    }

    public String getNodeCaption() {
        return nodeCaption;
    }

    public void setNodeCaption(String nodeCaption) {
        this.nodeCaption = nodeCaption;
    }
}
