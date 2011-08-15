/**
 * 
 */
package com.cristal.storm.prototype.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
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
    private List<DomainTimeCodesProxy> domainTimeCodes;
    
    private Boolean accountDataIsReady;
    private Boolean activityDataIsReady;
    private Boolean timeEntryDataIsReady;
    private Boolean timeCodeDataIsReady;

    final Map<TimeEntryProxy, AccountProxy> timeEntryAccountMap = new HashMap<TimeEntryProxy, AccountProxy>();
    final Map<TimeEntryProxy, ActivityProxy> timeEntryActivityMap = new HashMap<TimeEntryProxy, ActivityProxy>();

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
        domainTimeCodes = new ArrayList<DomainTimeCodesProxy>();
        setActivityData(new ArrayList<ActivityProxy>());
        setTimeEntryData(new ArrayList<TimeEntryProxy>());
        
        //
        accountDataIsReady = false;
        activityDataIsReady = false;
        timeEntryDataIsReady = false;
        timeCodeDataIsReady = false;
        
        // TODO:  Remove binded events;
        // Attach to the events from data inputs
        eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), getListAccountHandler(DATA_EVENT_TYPE.LIST_ALL_ACCOUNTS));
        eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), getListAccountHandler(DATA_EVENT_TYPE.LIST_ALL_ACTIVITIES));
        eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), getListAccountHandler(DATA_EVENT_TYPE.LIST_ALL_TIME_ENTRIES));
        eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), getListAccountHandler(DATA_EVENT_TYPE.LIST_ALL_TIME_CODES));

        eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {

            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent,
                    DATA_EVENT_TYPE eventType) {
                if (eventType == DATA_EVENT_TYPE.PREPARE_DATA) {
                    updateTimeEntryMapping();
                }
            }
        });
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

    private void listAllAccountsData() {
        watchDog.listAllAccounts(accountData);
    }
    
    private void listAllActivitiesData() {
        watchDog.listAllActivies(getActivityData());
    }
    
    private void listAllTimeEntries(Date startDate, Date endDate) {
        watchDog.listAllTimeEntriesInRange(getTimeEntryData(), startDate, endDate);
    }
    
    private void listAllTimeCodes() {
        watchDog.listAllTimeCodes(getDomainTimeCodesProxy());
    }
  
    public void getAllDataForUser(Date startDate, Date endDate) {
        listAllAccountsData();
        listAllActivitiesData();
        listAllTimeCodes();
        listAllTimeEntries(startDate, endDate);
    }
    
    /**
     * Clear all the data fetched from the DataStore
     */
    public void clearData() {
        accountData.clear();
        activityData.clear();
        timeEntryData.clear();
        domainTimeCodes.clear();
        
        accountDataIsReady = false;
        activityDataIsReady = false;
        timeEntryDataIsReady = false;
        timeCodeDataIsReady = false;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }
    
    /**
     * 
     */
    protected void updateTimeEntryMapping() {
        // Create a map of time entries (key) to accounts (values) for fast access
        // of user data
        for (TimeEntryProxy timeEntry : getTimeEntryData()) {
            Long ownAccountId = timeEntry.getOwningAccountId();
            
            for (AccountProxy curAccount : getAccountData()) {
                Long curAccoundId = curAccount.getId();
                
                // Map the values
                if (ownAccountId.longValue() == curAccoundId.longValue()) {
                    timeEntryAccountMap.put(timeEntry, curAccount);
                }
            }
        }
        
        // Create a map of time entries (key) to activities (values) for fast access
        // of user data
        for (TimeEntryProxy timeEntry : getTimeEntryData()) {
            Long curActivityId = timeEntry.getOwningActivityId();
            
            for (ActivityProxy curActivity : getActivityData()) {
                
                // Map the values
                if (curActivityId.longValue() == curActivity.getId().longValue()) {
                    timeEntryActivityMap.put(timeEntry, curActivity);
                }
            }
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////
    
    public Boolean isDataReady() {
        return accountDataIsReady & activityDataIsReady & timeEntryDataIsReady & timeCodeDataIsReady;
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
        else if (dataEventType == DATA_EVENT_TYPE.LIST_ALL_TIME_CODES) {
            timeCodeDataIsReady = true;
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
    
    public List<DomainTimeCodesProxy> getDomainTimeCodesProxy() {
       return domainTimeCodes;
    }
    
    public AccountProxy getAssociatedAccountProxy(TimeEntryProxy timeEntry) {
        return timeEntryAccountMap.get(timeEntry);
    }
    
    public ActivityProxy getAssociatedActivityProxy(TimeEntryProxy timeEntry) {
        return timeEntryActivityMap.get(timeEntry);
    }

}
