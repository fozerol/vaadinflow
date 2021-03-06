package com.mycompany.mavenproject3;


import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;
import java.util.ResourceBundle;
import javax.inject.Inject;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Theme("mytheme")
@CDIUI("")
@SuppressWarnings("serial")


public class MyUI extends UI {
    @Inject PublicComponent publiccontent;
    @Inject PrivateComponent privatecontent;

    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    
        if (!AuthService.isAuthenticated()) {
            showPublicComponent();
        } else {
            showPrivateComponent();
        }
    }
    
        

    public void showPublicComponent() {
        setContent(publiccontent);
    }

    public void showPrivateComponent() {
        privatecontent.initMenu();
        setContent(privatecontent);
    }       
}
