/**
 *
 *
 * @author Jose Rose
 * Dec 28, 2010
 */
package com.cristal.storm.prototype.client.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;

/**
 * PatientRequiredInfo Presenter's view
 *
 */
public class PatientRequiredInfoView extends ViewImpl implements
        PatientRequiredInfoPresenterWidget.PatientRequiredInfoViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private final VerticalPanel mainPanel;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public PatientRequiredInfoView(VerticalPanel mainPanel) {
        this.mainPanel = mainPanel;
        
/*        final FormPanel form2 = new FormPanel();
        form2.setLayout(new FlowLayout());
        form2.setWidth(400);
        form2.setBorders(false);
        form2.setBodyBorder(false);

        FieldSet fieldSet = new FieldSet();  
        fieldSet.setHeading("User Information");  
        fieldSet.setCheckboxToggle(true);  
      
        FormLayout layout = new FormLayout();  
        layout.setLabelWidth(75);  
        fieldSet.setLayout(layout);  
      
        final FormData formData = new FormData();
        TextField<String> firstName = new TextField<String>();  
        firstName.setFieldLabel("First Name");  
        firstName.setAllowBlank(false);  
        fieldSet.add(firstName, formData);  
      
        TextField<String> lastName = new TextField<String>();  
        lastName.setFieldLabel("Last Name");  
        fieldSet.add(lastName, formData);  
      
        TextField<String> company = new TextField<String>();  
        company.setFieldLabel("Company");  
        fieldSet.add(company, formData);  
      
        TextField<String> email = new TextField<String>();  
        email.setFieldLabel("Email");  
        fieldSet.add(email, formData);

        form2.add(fieldSet);*/

        mainPanel.add(new Label("Patient info view"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return mainPanel;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
