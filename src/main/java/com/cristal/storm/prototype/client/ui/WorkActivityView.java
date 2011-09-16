/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.client.ui;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.client.i18n.UtilFunc;
import com.cristal.storm.prototype.client.ui.Portlet.PortletUiBinder;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
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
    
    @UiField ListBox accountBox;
    @UiField ListBox activityBox;
    @UiField TextBox timeEntryTime;
    @UiField VerticalPanel editableContent;
    @UiField VerticalPanel viewableContent;
    
    @UiField Label lblAccount;
    @UiField Label lblActivity;
    @UiField Label lblActivityTime;
    
    @UiField Label workedTimeLbl;
    @UiField Label timeCodeLbl;
    @UiField Label associationLbl;
    
    private final ContentDisplayType contentType;
    private final Widget rootWidget;
    private final TimeEntryProxy timeEntry;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public WorkActivityView(final Provider<AppsConstants> appCteProvider,
            ContentDisplayType contentType,
            final DataStoreProxy dataStoreProxy,
            final TimeEntryProxy timeEntry) {
        rootWidget = uiBinder.createAndBindUi(this);
        initWidget(rootWidget);
        
        this.timeEntry = timeEntry;
        
        // Populate the time entry boxes
        int accountBoxIdx = 0;
        int selectedAccountBoxIdx = 0;
        AccountProxy associatedAccount = dataStoreProxy.getAssociatedAccountProxy(timeEntry);
        for (AccountProxy curAccount : dataStoreProxy.getAccountData()) {
            accountBox.addItem(curAccount.getName());
            
            if (associatedAccount == curAccount) {
                selectedAccountBoxIdx = accountBoxIdx;
            }
            accountBoxIdx++;
        }
        accountBox.setSelectedIndex(selectedAccountBoxIdx);

        int activityBoxIdx = 0;
        int selectedActiviyBoxIdx = 0;
        ActivityProxy associatedActivity = dataStoreProxy.getAssociatedActivityProxy(timeEntry);
        for (ActivityProxy curActivity : dataStoreProxy.getActivityData()) {
            activityBox.addItem(curActivity.getName());
            
            if (associatedActivity == curActivity) {
                selectedActiviyBoxIdx = activityBoxIdx;
            }            
            activityBoxIdx++;
        }
        activityBox.setSelectedIndex(selectedActiviyBoxIdx);
        
        // The the time spent
        timeEntryTime.setText(Double.toString(timeEntry.getSpentTime()));
                
        this.contentType = contentType;

        // Set the correct language
        AppsConstants appCte = appCteProvider.get();
        lblAccount.setText(appCte.account() + ":");
        lblActivity.setText(appCte.activity() + ":");
        lblActivityTime.setText(appCte.activityTime() + ":");
        
        if (contentType == ContentDisplayType.EDITABLE) {
            viewableContent.setVisible(false);
        }
        else {
            editableContent.setVisible(false);
        }
        
        // Set the viewable content
        workedTimeLbl.setText(Double.toString(timeEntry.getSpentTime()));
        timeCodeLbl.setText(UtilFunc.getTimeCodeValue(timeEntry, dataStoreProxy.getDomainTimeCodeMap()));
        associationLbl.setText(accountBox.getValue(accountBox.getSelectedIndex()) + " / " + activityBox.getValue(activityBox.getSelectedIndex()));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public Widget asWidget() {
        if (contentType == ContentDisplayType.EDITABLE) {
            viewableContent.setVisible(false);
        }
        else {
            editableContent.setVisible(false);
        }
        return rootWidget;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
