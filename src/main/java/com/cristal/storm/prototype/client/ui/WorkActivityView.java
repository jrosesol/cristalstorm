/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.client.ui;

import java.util.Date;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.client.i18n.UtilFunc;
import com.cristal.storm.prototype.client.ui.Portlet.PortletUiBinder;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * TODO: Add comments for WorkActivityView
 *
 */
public class WorkActivityView extends Composite implements Editor<TimeEntryProxy> {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    interface WorkActivityViewUiBinder extends UiBinder<Widget, WorkActivityView> {
    }

    private static WorkActivityViewUiBinder uiBinder = GWT.create(WorkActivityViewUiBinder.class);
    
    interface EditorDriver extends RequestFactoryEditorDriver<TimeEntryProxy, WorkActivityView> {}
    private final EditorDriver driver = GWT.create(EditorDriver.class);
    
    @UiField protected ListBox accountBox;
    @UiField protected ListBox activityBox;
    @UiField protected TextBox spentTime;
    @UiField protected VerticalPanel editableContent;
    @UiField protected VerticalPanel viewableContent;
    
    @Ignore @UiField protected Label lblAccount;
    @Ignore @UiField protected Label lblActivity;
    @Ignore @UiField protected Label lblActivityTime;
    
    @Ignore @UiField protected Label workedTimeLbl;
    @Ignore @UiField protected Label timeCodeLbl;
    @Ignore @UiField protected Label associationLbl;
    
    private final ContentDisplayType contentType;
    private final Widget rootWidget;
    private final TimeEntryProxy timeEntry;
    
    TimesheetRequestFactory factory = GWT.create( TimesheetRequestFactory.class );

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public WorkActivityView(final Provider<AppsConstants> appCteProvider,
            ContentDisplayType contentType,
            final DataStoreProxy dataStoreProxy,
            final TimeEntryProxy timeEntry,
            final EventBus eventBus) {
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
        //spentTime.setText(Double.toString(timeEntry.getSpentTime()));
                
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
        workedTimeLbl.setText(timeEntry.getSpentTime());
        timeCodeLbl.setText(UtilFunc.getTimeCodeValue(timeEntry, dataStoreProxy.getDomainTimeCodeMap()));
        associationLbl.setText(accountBox.getValue(accountBox.getSelectedIndex()) + " / " + activityBox.getValue(activityBox.getSelectedIndex()));
        
        // Initialize the editor

        factory.initialize( eventBus );
        driver.initialize(factory, this);
                
        driver.edit(this.timeEntry, factory.timeEntryRequest());
                
        //TimeEntryRequestContext a = (TimeEntryRequestContext) driver.flush();
        
        // Fire the request
        //a.listAll().fire(receiver);
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
    
    @UiHandler("saveButton")
    void handleSaveClick(ClickEvent e) {
        TimeEntryRequestContext timeEntryRC = (TimeEntryRequestContext) driver.flush();
        
        timeEntryRC.fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                Window.alert("Success!");
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
