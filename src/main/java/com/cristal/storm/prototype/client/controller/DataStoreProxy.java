/**
 * 
 */
package com.cristal.storm.prototype.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.CommandWatchDog;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.dispatch.shared.DispatchAsync;


/**
 * Keep the local data from the server.
 * + Load the user's accounts.
 * + Load the user's activities.
 * + Load the user's time entries.
 *  
 * @author Jose Rose
 * 
 */
public class DataStoreProxy implements HasHandlers {
    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    
    private final EventBus eventBus;
    private final CommandWatchDog watchDog;
    
    private List<AccountProxy> accountData;
    private List<ActivityProxy> activityData;
    private List<TimeEntryProxy> timeEntryData;
    
    private Boolean accountDataIsReady;
    private Boolean activityDataIsReady;
    private Boolean timeEntryDataIsReady;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    /**
     * 
     */
    @Inject
    public DataStoreProxy(final EventBus eventBus, final CommandWatchDog commandWatchDog) {
        this.eventBus = eventBus;
        this.watchDog = commandWatchDog;
        
        accountData = new ArrayList<AccountProxy>();
        setActivityData(new ArrayList<ActivityProxy>());
        setTimeEntryData(new ArrayList<TimeEntryProxy>());
        
        //
        accountDataIsReady = false;
        activityDataIsReady = false;
        timeEntryDataIsReady = false;
        
        // TODO:  Remove binded events;
        // Attach to the events from data inputs
        HandlerRegistration handlerReg = this.eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), getListAccountHandler(DATA_EVENT_TYPE.LIST_ALL_ACCOUNTS));
        this.eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), getListAccountHandler(DATA_EVENT_TYPE.LIST_ALL_ACTIVITIES));
        this.eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), getListAccountHandler(DATA_EVENT_TYPE.LIST_ALL_TIME_ENTRIES));

    }
    
    // /////////////////////////////////////////////////////////////////////////
    // Handlers
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Handle the data from the listing of all accounts.
     * @return the handler
     */
    private UpdateDataBindedObjectsHandler getListAccountHandler(final DATA_EVENT_TYPE responseEventType) {
        UpdateDataBindedObjectsHandler handler = new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {
            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent,
                    DATA_EVENT_TYPE eventType) {
                if (eventType == responseEventType) {
                    syncDataAndPresenters(responseEventType);
                }
            }
        };
        
        return handler;
    }
        

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    public void listAllAccountsData() {
        watchDog.listAllAccounts(accountData);
    }
    
    public void listAllActivitiesData() {
        watchDog.listAllActivies(getActivityData());
    }
    
    public void listAllTimeEntries() {
        watchDog.listAllTimeEntries(getTimeEntryData());
    }
    
    public void listAllTimeEntries(Date startDate, Date endDate) {
        watchDog.listAllTimeEntriesInRange(getTimeEntryData(), startDate, endDate);
    }
    
    public void getAllDataForUser() {
        listAllAccountsData();
        listAllActivitiesData();
        listAllTimeEntries();
    }
    
    public void getAllDataForUser(Date startDate, Date endDate) {
        listAllAccountsData();
        listAllActivitiesData();
        listAllTimeEntries(startDate, endDate);
    }
    
    /**
     * Clear all the data fetched from the DataStore
     */
    public void clearData() {
        accountData.clear();
        activityData.clear();
        timeEntryData.clear();
        
        accountDataIsReady = false;
        activityDataIsReady = false;
        timeEntryDataIsReady = false;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////
    
    public Boolean isDataReady() {
        return accountDataIsReady & activityDataIsReady & timeEntryDataIsReady;
    }
    
    public void syncDataAndPresenters(DATA_EVENT_TYPE dataEventType) {
        if (dataEventType == DATA_EVENT_TYPE.LIST_ALL_ACCOUNTS) {
            accountDataIsReady = true;
        }
        else if (dataEventType == DATA_EVENT_TYPE.LIST_ALL_ACTIVITIES) {
            activityDataIsReady = true;
        }

        else if (dataEventType == DATA_EVENT_TYPE.LIST_ALL_TIME_ENTRIES) {
            timeEntryDataIsReady = true;
        }
        else {
            throw new IllegalArgumentException();
        }

        
        if (isDataReady()) {
            
            // Get the data ready to be used
            UpdateDataBindedObjectsEvent.fire(eventBus, DATA_EVENT_TYPE.PREPARE_DATA);
            
            // The data is ready to be used
            UpdateDataBindedObjectsEvent.fire(eventBus, DATA_EVENT_TYPE.DATA_IS_AVAILABLE);
            
            // Reveal the presenters (update the presenters)
            UpdateDataBindedObjectsEvent.fire(eventBus, DATA_EVENT_TYPE.REVEAL_PRESENTERS);
        }
    }

    public List<AccountProxy> getAccountData() {
        return accountData;
    }

    private void setActivityData(List<ActivityProxy> activityData) {
        this.activityData = activityData;
    }

    public List<ActivityProxy> getActivityData() {
        return activityData;
    }

    private void setTimeEntryData(List<TimeEntryProxy> timeEntryData) {
        this.timeEntryData = timeEntryData;
    }

    public List<TimeEntryProxy> getTimeEntryData() {
        return timeEntryData;
    }

}
