/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity.auth;

import com.mycompany.mavenproject3.entity.TreeViewConfig;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "approles")
@NamedQuery(name="AppRole.findAll", query="SELECT e FROM AppRole e")
public class AppRole implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="treeviewconfigid",referencedColumnName = "id")
private TreeViewConfig treeviewconfig;
@OneToOne(fetch=FetchType.EAGER)
@JoinColumn(name="roleid")
private Role role;

    public AppRole() {
    }


    public AppRole(String name, TreeViewConfig treeviewconfig, Role role) {
        
        this.name = name;
        this.treeviewconfig = treeviewconfig;
        this.role = role;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeViewConfig getTreeviewconfig() {
        return treeviewconfig;
    }

    public void setTreeviewconfig(TreeViewConfig treeviewconfig) {
        this.treeviewconfig = treeviewconfig;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
