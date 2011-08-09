/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.client.ui;

import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.client.ui.Portlet.PortletUiBinder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * TODO: Add comments for WorkActivityView
 *
 */
public class WorkActivityView extends Composite {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    interface WorkActivityViewUiBinder extends UiBinder<Widget, WorkActivityView> {
    }
    
    private static WorkActivityViewUiBinder uiBinder = GWT.create(WorkActivityViewUiBinder.class);
    
    @UiField
    ListBox accountBox;
    @UiField
    ListBox activityBox;
    @UiField
    TextBox timeEntryTime;
    
    @UiField Label lblAccount;
    @UiField Label lblActivity;
    @UiField Label lblActivityTime;

    @Inject
    Provider<AppsConstants> appCteProvider;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public WorkActivityView() {
        initWidget(uiBinder.createAndBindUi(this));
        
        accountBox.addItem("TERAFLOP");
        accountBox.addItem("Some Other Project");
        activityBox.addItem("System integration");
        activityBox.addItem("System testing");
        activityBox.addItem("Module testing");

        // Set the correct language
        AppsConstants appCte = appCteProvider.get();
        lblAccount.setText(appCte.account() + ":");
        lblActivity.setText(appCte.activity() + ":");
        lblActivityTime.setText(appCte.activityTime() + ":");
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
