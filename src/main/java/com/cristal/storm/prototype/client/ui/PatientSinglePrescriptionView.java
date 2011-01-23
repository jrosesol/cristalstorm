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
import com.cristal.storm.prototype.client.mvp.presenter.*;

/**
 * PatientSinglePrescription Presenter's view
 * TODO: PatientSinglePrescription description
 */
public class PatientSinglePrescriptionView extends ViewImpl implements
        PatientSinglePrescriptionPresenterWidget.PatientSinglePrescriptionViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private final VerticalPanel mainPanel;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public PatientSinglePrescriptionView(final VerticalPanel mainPanel) {
        this.mainPanel = mainPanel;
        
/*        final FormPanel form2 = new FormPanel();
        form2.setLayout(new FlowLayout());
        form2.setWidth(400);
        form2.setBorders(false);
        form2.setBodyBorder(false);
        
        FieldSet fieldSet = new FieldSet();
        fieldSet = new FieldSet();
        fieldSet.setHeading("Phone Numbers");
        fieldSet.setCollapsible(true);

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(75);
        fieldSet.setLayout(layout);

        final FormData formData = new FormData();
        TextField<String> field = new TextField<String>();
        field.setFieldLabel("Home");
        fieldSet.add(field, formData);

        field = new TextField<String>();
        field.setFieldLabel("Business");
        fieldSet.add(field, formData);

        field = new TextField<String>();
        field.setFieldLabel("Mobile");
        fieldSet.add(field, formData);

        field = new TextField<String>();
        field.setFieldLabel("Fax");
        fieldSet.add(field, formData);

        form2.add(fieldSet);*/

        mainPanel.add(new Label("patient single prescription"));
        //mainPanel.layout();
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

