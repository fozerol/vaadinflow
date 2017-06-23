package com.mycompany.mavenproject3;


import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;
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
    public Component findComponentWithId(HasComponents root, String id) {
    for(Component child : root) {
        if(id.equals(child.getId())) {
            // found it!
            return child;
        } else if(child instanceof HasComponents) {
            // recursively go through all children that themselves have children
                Component ret= findComponentWithId((HasComponents) child, id);
                if(ret!=null)return ret;

        }
    }
    // none was found
    return null;
  }
        
/*        
        
        final HorizontalLayout rootlayout = new HorizontalLayout();
        final VerticalLayout layout = new VerticalLayout();
        final TextField name = new TextField();
                        Button button1 = new Button("Click1");
                        Button button2 = new Button("Click2");
        
        name.setCaption("Type your name here:");

//        TreeViewUI tvui = new  TreeViewUI();
        layout.addComponents(button1,button2,name);
        
        HorizontalLayout content = new HorizontalLayout();
        content.setId("horizon");
        n = new Navigator(this,content);
        rootlayout.addComponents(layout,tvui,content);
        try {
                  initMenu();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
  setContent(rootlayout);*/
//                String className = "com.mycompany.mavenproject3.View1";
//          TreeViewConfig tree = new TreeViewConfig();
  //      TestClass testClass = new TestClass("fatih1","ozerol2");
//        TestClassDao tc = new TestClassDao();
//        tc.create(testClass);
        //@Inject FatihClass f;

//        navigator.navigateTo("");
//        AppConfigDao a = new AppConfigDao();
  //      a.create(tree);
     //   Class xyz;
        //String className = "View1";
   /*     try {
           xyz = Class.forName(className).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }*/
//   String className ="View1" ;
   /*Class<?> panel = null;
   View newScreen = null;
        try {
            panel = Class.forName( className );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
           newScreen = (View) panel.newInstance();
            
            
            
        } catch (InstantiationException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        n.addView("", newScreen);*/
            //n.addView("view2", View2.class);

      /*  
        button1.addClickListener( e -> {
            //layout.addComponent(new Label("Thanks " + name.getValue()                     + ", it works!"));
            n.navigateTo("");
        });

        button2.addClickListener( e -> {
            //layout.addComponent(new Label("Thanks " + name.getValue()                     + ", it works!"));
            n.navigateTo("view2");
        });
*/
        
        //layout.addComponents(name, button);

     /* final TreeTable ttable = new TreeTable("My TreeTable");
        ttable.addContainerProperty("Name", String.class, "");
        ttable.setWidth("20em");
        
        // Create the tree nodes
        ttable.addItem(new Object[]{"Root"}, 0);
        ttable.addItem(new Object[]{"Branch 1"}, 1);
        ttable.addItem(new Object[]{"Branch 2"}, 2);
        ttable.addItem(new Object[]{"Leaf 1"}, 3);
        ttable.addItem(new Object[]{"Leaf 2"}, 4);
        ttable.addItem(new Object[]{"Leaf 3"}, 5);
        ttable.addItem(new Object[]{"Leaf 4"}, 6);
        
        // Set the hierarchy
        ttable.setParent(1, 0);
        ttable.setParent(2, 0);
        ttable.setParent(3, 1);
        ttable.setParent(4, 1);
        ttable.setParent(5, 2);
        ttable.setParent(6, 2);
        
        // Expand the tree
        ttable.setCollapsed(2, false);
        //for (Object itemId: tree.getItemIds())
        //    tree.setCollapsed(itemId, false);
        
        Button foo = new Button("Foo", new Button.ClickListener() {
            private static final long serialVersionUID = 8903978809209811750L;




          @Override
          public void buttonClick(Button.ClickEvent event) {

                              Object newItemId = ttable.addItemAfter(1);
                ttable.getContainerProperty(newItemId, "Name").setValue("New One");
                ttable.setParent(newItemId, 2);

          }
        
        });
        // END-EXAMPLE: component.treetable.basic
        
        rootlayout.addComponent(ttable);
        rootlayout.addComponent(foo);
        setContent(rootlayout);*/
        
        
        
        
}
   



