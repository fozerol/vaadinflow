/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import com.mycompany.mavenproject3.customcomponent.FTwinColSelect;
import com.mycompany.mavenproject3.flow.Flow;
import com.mycompany.mavenproject3.flow.FlowFormData;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.AbstractListing;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatih
 */
public class ComponentFinder {
    //public static List<Component> components = null;
    public static Component findComponentWithId(HasComponents root, String id) {
    for(Component child : root) {
        if(child.getId() != null && id.equals(child.getId())) {
            // found it!
            return child;
        } else if(child instanceof HasComponents) {
            // recursively go through all children that themselves have children
                Component ret= findComponentWithId((HasComponents) child, id);
                if(ret!=null)
            return ret;
        }
        }
        return null;
    }
    public static List<Component> findAllComponents(HasComponents root,List<Component> components)
    {
        
        if (components == null)
                components = new ArrayList<>();
        
        for(Component child : root) {
            if(child instanceof HasComponents) {
                components = findAllComponents((HasComponents) child ,components);
            }
            else
            {
                if (child.getId() != null)
                components.add(child);
            }
            }
        return components;
     }
    public static List<FlowFormData> getFlowFormDatas(HasComponents root,Flow flow){
        
        List<Object> gridcontent = null;
        List<Component> components = null;
        List<FlowFormData> flowformdatas = new ArrayList<>();
        
        flowformdatas = getFlowFormData(root,components,flowformdatas,gridcontent,flow);
        return flowformdatas;
    }
    public static byte[] convertToByte (Object object)
            
            {
                byte[] byteArray = null;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
try {
  out = new ObjectOutputStream(bos);   
  out.writeObject(object);
  out.flush();
  byteArray = bos.toByteArray();
  
} finally {
  try {
    bos.close();
  } catch (IOException ex) {
    // ignore close exception
  }
  return byteArray;
}
            }
    public static List<FlowFormData> getFlowFormData(HasComponents root ,List<Component> components,List<FlowFormData> flowformdatas, List<Object> content,Flow flow)
    {
        FlowFormData flowformdata = null;
        for(Component child : root) {
                if (child instanceof Grid)
                {
                    flowformdata = new FlowFormData();
                    setFlowFormDataProperties(flowformdata,child,flow);
                    content =  (List<Object>)((ListDataProvider)(((Grid) child).getDataProvider())).getItems();
                    flowformdata.setConatinervalue(convertToByte(content));
                    flowformdatas.add(flowformdata);
                }
                else if (child instanceof TextField)
                {
                    flowformdata = new FlowFormData();
                    setFlowFormDataProperties(flowformdata,child,flow);
                    flowformdata.setValue(((TextField) child).getValue());
                    flowformdatas.add(flowformdata);
                        
                }
                else if (child instanceof FTwinColSelect)
                {
                    flowformdata = new FlowFormData();
                    setFlowFormDataProperties(flowformdata,child,flow);
                    content =  (List<Object>)((FTwinColSelect) child).getValueAsList();
                    flowformdata.setConatinervalue(convertToByte(content));
                    flowformdatas.add(flowformdata);
                }
                else if (child instanceof ComboBox)
                {
                    flowformdata = new FlowFormData();
                    setFlowFormDataProperties(flowformdata,child,flow);
                     Object o = null;
                     o=((ComboBox)child).getValue();
                     flowformdata.setConatinervalue(convertToByte(o));
                    flowformdatas.add(flowformdata);
                        
                }
                else if(child instanceof HasComponents) {
                    flowformdatas = getFlowFormData((HasComponents) child ,components,flowformdatas,content,flow);
                }

                else
                {
                    flowformdata = new FlowFormData();
                    setFlowFormDataProperties(flowformdata,child,flow);
                    flowformdata.setValue(null);
                    flowformdatas.add(flowformdata);
                }
            }
    return flowformdatas;
    }
    public static void setFlowFormDataProperties(FlowFormData flowformdata,Component child,Flow flow){
                    //flowformdata = new FlowFormData();
                    flowformdata.setObjectId(child.getId());
                    flowformdata.setFlow(flow);
                    flowformdata.setObjectType(child.getClass().toString().replace("class ", ""));
                    flowformdata.setIsHidden(!child.isVisible());
                    flowformdata.setIsReadOnly(!child.isEnabled());
    }
    public static void setObjectProperties(FlowFormData flowformdata,Component component)
    {
                    component.setEnabled(!flowformdata.isIsReadOnly());
                    component.setVisible(!flowformdata.isIsHidden());
    }
    public static <T> T convertFromBytes(byte[] bytes,T t) throws IOException, ClassNotFoundException{
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
    ObjectInput in = null;
    //List<Object> o = null;
    T o;
try {
  in = new ObjectInputStream(bis);
  o = (T) in.readObject(); 

} finally {
  try {
    if (in != null) {
      in.close();
    }
  } catch (IOException ex) {

  }
}
    return o;
    }
    public static void loadFormData(HasComponents ui,List<FlowFormData> flowformdatas){
        for (FlowFormData f : flowformdatas)
        {
            Component c = findComponentWithId (ui,f.getObjectId());
            if ( c instanceof TextField)
            {
             ((TextField)c).setValue(f.getValue());
            }
            else if (c instanceof Grid)
            {
                try {
                    List<Object> o = null;
                    ((Grid)c).setItems(convertFromBytes(f.getConatinervalue(),o));
                } catch (IOException ex) {
                    Logger.getLogger(ComponentFinder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ComponentFinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (c instanceof FTwinColSelect)
            {
                try {
                    List<Object> o = null;
                    //((TwinColSelect)c).setItems(convertFromBytes(f.getConatinervalue(),o));
                    ((FTwinColSelect) ((TwinColSelect)c)).setSelected(convertFromBytes(f.getConatinervalue(),o));
                } catch (IOException ex) {
                    Logger.getLogger(ComponentFinder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ComponentFinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (c instanceof ComboBox)
            {
                try {
                    Object o = null;
                    ((ComboBox)c).setValue(convertFromBytes(f.getConatinervalue(),o));
                } catch (IOException ex) {
                    Logger.getLogger(ComponentFinder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ComponentFinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                setObjectProperties(f,c);
            }
                
        }
    }
}
