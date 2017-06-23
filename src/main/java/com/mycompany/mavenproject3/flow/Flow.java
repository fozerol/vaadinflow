/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.flow;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "flows")
@NamedQueries({
@NamedQuery(name="Flow.findAll", query="SELECT e FROM Flow e where 1=1"),
@NamedQuery(name="Flow.countAll", query="SELECT COUNT(e) FROM Flow e where 1=1")})



public class Flow implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String senderId;
private String starterId;
private Date startDate;
private Date sendDate;
private String receiverId;
private String flowform;
//@OneToMany
@OneToMany(mappedBy = "flow", cascade = CascadeType.ALL)
private List<FlowFormData> flowFormData;

    public Flow() {
    }

    public List<FlowFormData> getFlowFormData() {
        return flowFormData;
    }

    public void setFlowFormData(List<FlowFormData> flowFormData) {
        this.flowFormData = flowFormData;
    }



    public Flow(String senderId, String starterId, Date startDate, Date sendDate, String receiverId, String flowform) {
        this.senderId = senderId;
        this.starterId = starterId;
        this.startDate = startDate;
        this.sendDate = sendDate;
        this.receiverId = receiverId;
        this.flowform = flowform;
    }

    public Long getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getStarterId() {
        return starterId;
    }

    public void setStarterId(String starterId) {
        this.starterId = starterId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getFlowform() {
        return flowform;
    }

    public void setFlowform(String flowform) {
        this.flowform = flowform;
    }
    

}

      