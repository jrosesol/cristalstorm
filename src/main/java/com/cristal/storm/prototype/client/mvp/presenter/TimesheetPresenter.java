/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.event.RemovePortletsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.cristal.storm.prototype.client.mvp.view.TimesheetUiHandlers;
import com.cristal.storm.prototype.client.ui.ActivityCalendarWidgetPresenter;
import com.cristal.storm.prototype.client.ui.Portlet;
import com.cristal.storm.prototype.client.ui.ActivityCalendarWidgetPresenter.ActivityCalendarWidgetViewInterface;
import com.cristal.storm.prototype.client.ui.ActivityCalendarWidgetView;
import com.cristal.storm.prototype.client.util.Resources;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.CommandWatchDog;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;

/**
 * Timesheet Presenter implementation
 * This will handle all timesheet actions
 */
public class TimesheetPresenter extends
        Presenter<TimesheetPresenter.TimesheetViewInterface, TimesheetPresenter.TimesheetProxy>
        implements TimesheetUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "timesheet";
    
    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

    @Inject
    TimesheetCellListPresenter timesheetCellList;
    
    @Inject
    DataStoreProxy dataStoreProxy;
    
    @Inject
    CommandWatchDog commandWatchDog;
    
    @Inject
    final EventBus eventBus;


    final Map<TimeEntryProxy, AccountProxy> timeEntryAccountMap = new HashMap<TimeEntryProxy, AccountProxy>();
    final Map<TimeEntryProxy, ActivityProxy> timeEntryActivityMap = new HashMap<TimeEntryProxy, ActivityProxy>();
    
    private final ActivityCalendarWidgetPresenter mondayActivities;
    private final ActivityCalendarWidgetPresenter thuesdayActivities;
    private final ActivityCalendarWidgetPresenter wednesdayActivities;
    private final ActivityCalendarWidgetPresenter thursdayActivities;
    private final ActivityCalendarWidgetPresenter fridayActivities;
    private final ActivityCalendarWidgetPresenter saturdayActivities;
    private final ActivityCalendarWidgetPresenter sundayActivities;
    
    /*The project detail popup*/
    private final ProjectPopupDetailsPresenter projectDetailsPopup;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link TimesheetPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface TimesheetProxy extends ProxyPlace<TimesheetPresenter> {
    }

    /**
     * {@link TimesheetPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface TimesheetViewInterface extends View,
            HasUiHandlers<TimesheetUiHandlers> {
        public void setHandlers(final EventBus eventBus, final CommandWatchDog commandWatchDog);
        public void setActivityCalendarWidget(ActivityCalendarWidgetViewInterface activityCalendar);
        public Date getStartWeekTime();
        public Date getEndWeekTime();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public TimesheetPresenter(EventBus eventBus, TimesheetViewInterface view, TimesheetProxy proxy,
            PlaceManager placeManager, final ProjectPopupDetailsPresenter projectPopup,
            final ActivityCalendarWidgetPresenter mondayActivities,
            final ActivityCalendarWidgetPresenter thuesdayActivities, 
            final ActivityCalendarWidgetPresenter wednesdayActivities,
            final ActivityCalendarWidgetPresenter thursdayActivities,
            final ActivityCalendarWidgetPresenter fridayActivities, 
            final ActivityCalendarWidgetPresenter saturdayActivities, 
            final ActivityCalendarWidgetPresenter sundayActivities   ) {
        super(eventBus, view, proxy);
        getView().setUiHandlers(this);

        this.eventBus = eventBus;
        
        this.projectDetailsPopup = projectPopup;
        this.mondayActivities = mondayActivities;
        this.thuesdayActivities = thuesdayActivities;
        this.wednesdayActivities = wednesdayActivities; 
        this.thursdayActivities = thursdayActivities;
        this.fridayActivities = fridayActivities; 
        this.saturdayActivities = saturdayActivities;
        this.sundayActivities = sundayActivities;   

        getView().setHandlers(eventBus, commandWatchDog);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see com.gwtplatform.mvp.client.HandlerContainerImpl#onBind()
     */
    @Override
    protected void onBind() {
        
        // Reveal the presenter when data is ready
        addRegisteredHandler(UpdateDataBindedObjectsEvent.getType(), new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {

            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent,
                    DATA_EVENT_TYPE eventType) {
                
                if (eventType == DATA_EVENT_TYPE.REVEAL_PRESENTERS) {
                    //addToPopupSlot(projectDetailsPopup);
                    
                    getProxy().manualReveal(TimesheetPresenter.this);
                }
            }
        } );

        
        // Create a high level representation of the data, that is a map of 
        // time entries for each activity for each account.
        registerHandler(eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {

            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent,
                    DATA_EVENT_TYPE eventType) {
                if (eventType == DATA_EVENT_TYPE.PREPARE_DATA) {
                    
                    // Create a map of time entries (key) to accounts (values) for fast access
                    // of user data
                    for (TimeEntryProxy timeEntry : dataStoreProxy.getTimeEntryData()) {
                        Long curAccountId = timeEntry.getOwningAccountId();
                        
                        for (AccountProxy curAccount : dataStoreProxy.getAccountData()) {
                            
                            // Map the values
                            if (curAccountId == curAccount.getId()) {
                                timeEntryAccountMap.put(timeEntry, curAccount);
                            }
                        }
                    }
                    
                    // Create a map of time entries (key) to activities (values) for fast access
                    // of user data
                    for (TimeEntryProxy timeEntry : dataStoreProxy.getTimeEntryData()) {
                        Long curActivityId = timeEntry.getOwningActivityId();
                        
                        for (ActivityProxy curActivity : dataStoreProxy.getActivityData()) {
                            
                            // Map the values
                            if (curActivityId == curActivity.getId()) {
                                timeEntryActivityMap.put(timeEntry, curActivity);
                            }
                        }
                    }
                }
            }
        }));

    }
    
    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, 
                this);
    }
    
    @Override
    public boolean useManualReveal() {        
        return true;
    }
    
    @Override
    protected void onReveal() {
        // TODO Auto-generated method stub
        super.onReveal();
        
        getView().setActivityCalendarWidget(mondayActivities.getView());
        getView().setActivityCalendarWidget(thuesdayActivities.getView());
        getView().setActivityCalendarWidget(wednesdayActivities.getView());
        getView().setActivityCalendarWidget(thursdayActivities.getView());
        getView().setActivityCalendarWidget(fridayActivities.getView());
        getView().setActivityCalendarWidget(saturdayActivities.getView());
        getView().setActivityCalendarWidget(sundayActivities.getView());
    }
    
    @Override
    public void prepareFromRequest(PlaceRequest request) {
        
        // TODO Auto-generated method stub
        super.prepareFromRequest(request);
        
        
        setActivityCalendarWidgetDate();

        // Load the time entry data
        dataStoreProxy.getAllDataForUser(getView().getStartWeekTime(), getView().getEndWeekTime());
        
    }

    private void setActivityCalendarWidgetDate() {
        mondayActivities.setCalendarDate(getView().getStartWeekTime());
        
        Date thusdayWeekDate = new Date(getView().getStartWeekTime().getTime());
        CalendarUtil.addDaysToDate(thusdayWeekDate, 1);
        thuesdayActivities.setCalendarDate(thusdayWeekDate);
        
        Date wednesdayWeekDate = new Date(thusdayWeekDate.getTime());
        CalendarUtil.addDaysToDate(wednesdayWeekDate, 1);
        wednesdayActivities.setCalendarDate(wednesdayWeekDate);
        
        Date thurdayWeekDate = new Date(wednesdayWeekDate.getTime());
        CalendarUtil.addDaysToDate(thurdayWeekDate, 1);
        thursdayActivities.setCalendarDate(thurdayWeekDate);
        
        Date fridayWeekDate = new Date(thurdayWeekDate.getTime());
        CalendarUtil.addDaysToDate(fridayWeekDate, 1);
        fridayActivities.setCalendarDate(fridayWeekDate);
        
        Date saturdayWeekDate = new Date(fridayWeekDate.getTime());
        CalendarUtil.addDaysToDate(saturdayWeekDate, 1);
        saturdayActivities.setCalendarDate(saturdayWeekDate);
        
        Date sundayWeekDate = new Date(saturdayWeekDate.getTime());
        CalendarUtil.addDaysToDate(sundayWeekDate, 1);
        sundayActivities.setCalendarDate(sundayWeekDate);
    }
    
    @Override
    protected void onHide() {
        super.onHide();
        
        // Clear all data from the DataStore proxy
        dataStoreProxy.clearData();

        // Fire an event to remove old portlets.
        RemovePortletsEvent.fire(this);
    }

    /* (non-Javadoc)
     * @see com.cristal.storm.prototype.client.mvp.view.TimesheetUiHandlers#saveTimeEntries()
     */
    @Override
    public void saveTimeEntries() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateTimeEntriesForDateRangeChange() {
        
        // Clear all data from the DataStore proxy
        dataStoreProxy.clearData();

        // Fire an event to remove old portlets.
        RemovePortletsEvent.fire(this);
        setActivityCalendarWidgetDate();
        
        // Load the time entry data        
        dataStoreProxy.getAllDataForUser(getView().getStartWeekTime(), getView().getEndWeekTime());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}