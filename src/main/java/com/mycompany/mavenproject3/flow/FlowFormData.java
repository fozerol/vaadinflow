/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.flow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "flowformdatas")
@NamedQueries({
@NamedQuery(name="FlowFormData.findAll", query="SELECT e FROM Flow e where 1=1"),
@NamedQuery(name="FlowFormData.countAll", query="SELECT COUNT(e) FROM Flow e where 1=1")})
public class FlowFormData {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@ManyToOne
@JoinColumn(name = "flowid", referencedColumnName = "id")
private Flow flow;
private String objectId;
private String objectType;
@Lob
@Column(length=1000000)
private byte[] conatinervalue;
private String value;
private boolean isHidden;
private boolean isReadOnly;

    public FlowFormData() {
    }


    public FlowFormData( Flow flow, String objectId, String objectType, byte[] conatinervalue) {
        
        this.flow = flow;
        this.objectId = objectId;
        this.objectType = objectType;
        this.conatinervalue = conatinervalue;
    }

    public Long getId() {
        return id;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public byte[] getConatinervalue() {
        return conatinervalue;
    }

    public void setConatinervalue(byte[] conatinervalue) {
        this.conatinervalue = conatinervalue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean isIsReadOnly() {
        return isReadOnly;
    }

    public void setIsReadOnly(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }
}
