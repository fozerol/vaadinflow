/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.helper;

import com.mycompany.mavenproject3.flow.Flow;

/**
 *
 * @author fatih
 */
public interface FlowForm {
    public void setFlow(Flow flow);
    public Flow getFlow();
    public void loadFlowFormData();
}
