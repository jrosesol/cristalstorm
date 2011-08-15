/**
 *
 *
 * @author Jose Rose
 * 2011-04-29
 */
package com.cristal.storm.prototype.client.mvp.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.cristal.storm.prototype.client.mvp.presenter.ProjectPopupDetailsPresenter.ProjectPopupDetailsViewInterface;
import com.cristal.storm.prototype.client.mvp.presenter.TimeEntryWizardPopupPresenter.TimeEntryWizardPopupViewInterface;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PopupViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * ProjectPopupDetails Presenter's view
 * 
 */
public class TimeEntryWizardPopupView extends PopupViewImpl implements
    TimeEntryWizardPopupViewInterface {

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    
    private static ProjectPopupDetailsViewUiBinder uiBinder = GWT.create(ProjectPopupDetailsViewUiBinder.class);
    
    @UiField
    protected PopupPanel widget;
    
    @UiField
    protected VerticalPanel wizardContent;
    
    @UiField
    protected ListBox timeEntryTypes;
    
    @UiField
    protected Button okButton;
    

    // /////////////////////////////////////////////////////////////////////////
    // Interfaces
    // /////////////////////////////////////////////////////////////////////////

    public interface ProjectPopupDetailsViewUiBinder extends UiBinder<PopupPanel, TimeEntryWizardPopupView> {
    }
    
    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    @Inject
    public TimeEntryWizardPopupView(EventBus eventBus) {
        
        super(eventBus);

        // Has to be at the beginning
        widget = uiBinder.createAndBindUi(this);
      
    }

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }    

    @Override
    public void setInSlot(Object slot, Widget content) {
        wizardContent.clear();
                
        if (content != null) {            
            wizardContent.add(content);
        }
    }
    
    @Override
    public int getSelectedTimeCodeIdx() {
        return timeEntryTypes.getSelectedIndex();
    }
    
    @Override
    public void setTimeEntryCodes(List<String> domainTimeCodes) {
        timeEntryTypes.clear();
        // Add the possible time codes
        for (String timeCodeType : domainTimeCodes) {
            timeEntryTypes.addItem(timeCodeType);
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    
    @UiHandler("okButton")
    void okButtonClicked(ClickEvent event) {
        widget.hide();
    }
    
    @UiHandler("cancelButton")
    void cancelButtonClicked(ClickEvent event) {
        widget.hide();
    }

    @Override
    public HasClickHandlers onWizardOkButton() {
        return okButton;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}
