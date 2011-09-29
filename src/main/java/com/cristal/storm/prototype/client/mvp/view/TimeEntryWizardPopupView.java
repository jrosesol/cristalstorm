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
import java.util.Map;
import java.util.Set;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.client.mvp.presenter.ProjectPopupDetailsPresenter.ProjectPopupDetailsViewInterface;
import com.cristal.storm.prototype.client.mvp.presenter.TimeEntryWizardPopupPresenter.TimeEntryWizardPopupViewInterface;
import com.cristal.storm.prototype.client.ui.ContentDisplayType;
import com.cristal.storm.prototype.client.ui.ListBoxWithKey;
import com.cristal.storm.prototype.client.ui.WorkActivityView;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
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
import com.google.gwt.event.dom.client.ChangeEvent;
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
import com.google.inject.Provider;
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
    
    @UiField protected PopupPanel widget;
    
    @UiField protected VerticalPanel wizardContent;
    
    @UiField protected ListBoxWithKey timeEntryTypes;
    
    @UiField protected Button okButton;
    
    @UiField protected SimplePanel timeEntryConf;
    
    final Provider<AppsConstants> appCteProvider;
    final Provider<TimeEntryRequestContext> timeEntryContextProvider;
    final DataStoreProxy dataStoreProxy;
    final EventBus eventBus;

    // /////////////////////////////////////////////////////////////////////////
    // Interfaces
    // /////////////////////////////////////////////////////////////////////////

    public interface ProjectPopupDetailsViewUiBinder extends UiBinder<PopupPanel, TimeEntryWizardPopupView> {
    }
    
    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    @Inject
    public TimeEntryWizardPopupView(final EventBus eventBus,
            final Provider<AppsConstants> appCteProvider,
            final DataStoreProxy dataStoreProxy,
            final Provider<TimeEntryRequestContext> timeEntryContextProvider) {
        
        super(eventBus);

        // Has to be at the beginning
        widget = uiBinder.createAndBindUi(this);
        
        this.appCteProvider = appCteProvider;
        this.dataStoreProxy = dataStoreProxy;
        this.timeEntryContextProvider = timeEntryContextProvider;
        this.eventBus = eventBus;
        
    }

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }    
    
    @Override
    public TimeEntryProxy getTimeEntryProxy() {
        return wizardTimeEntryProxy;
    }
    
    @Override
    public void setTimeEntryCodes(Map<Long, String> domainTimeCodes) {
        
        timeEntryTypes.clear();
        
        Set<Long> keySet = domainTimeCodes.keySet();
        
        // Add the possible time codes
        for (Long key : keySet) {
            timeEntryTypes.addItem(domainTimeCodes.get(key), key);
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
    
    TimeEntryProxy wizardTimeEntryProxy = null;
    
    @UiHandler("timeEntryTypes")
    void onTimeEntrySelect(ChangeEvent event) {
        timeEntryConf.clear();
        
        wizardTimeEntryProxy = timeEntryContextProvider.get().create(TimeEntryProxy.class);
        wizardTimeEntryProxy.setTimeCode(timeEntryTypes.getKeyValue());
        
        if (timeEntryTypes.getKeyValue() == DomainTimeCodesProxy.NORMAL) {
            timeEntryConf.add(new WorkActivityView(appCteProvider, ContentDisplayType.EDITABLE, dataStoreProxy, wizardTimeEntryProxy, eventBus));
        } else {
            timeEntryConf.add(new Label(timeEntryTypes.getValue(timeEntryTypes.getSelectedIndex())));
        }
    }

    @Override
    public HasClickHandlers onWizardOkButton() {
        return okButton;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}
