/**
 *
 *
 * @author Jose Rose
 * 2011-09-27
 */
package com.cristal.storm.prototype.client.ui;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

/**
 * TODO: Add comments for BindedTextBox
 * @param <T>
 *
 */
public class BindedTextBox<T> extends TextBox {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    String proxyAccesorName;
    EntityProxy bindedEntity;
    Class<T> paramType;
    
    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    public BindedTextBox() {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        // TODO Auto-generated method stub
        return super.addChangeHandler(handler);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    private ChangeHandler changeHandler() {
        return new ChangeHandler() {

            @Override
            public void onChange(ChangeEvent event) {
                try {
                    // Read the value from the data entity proxy
                    Class paramTypes[] = new Class[1];
                    paramTypes[0] = paramType;
                    //Class entityClassObject = java.lang.reflect.forName("com.google.web.bindery.requestfactory.shared.EntityProxy");
                    //Method dataBindedMethod = entityClassObject.getMethod(proxyAccesorName, paramTypes);
                    Object arglist[] = new Object[1];
                    arglist[0] = null;
                    //Object retobj = dataBindedMethod.invoke(bindedEntity, arglist);

                    // Set the read value to the text box
                    //if (retobj != null) {
                        //setText(paramType.cast(retobj).toString());
                    //}
                    
                  }
                  catch (Throwable e) {
                     System.err.println(e);
                  }
            }
            
        };        
    }
    
    public void registerData(String proxyAccesorName, EntityProxy bindedEntity, Class<T> paramType) {
        this.proxyAccesorName = proxyAccesorName;
        this.bindedEntity = bindedEntity;
        this.paramType = paramType;        

        addChangeHandler(changeHandler());
    }
    

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
