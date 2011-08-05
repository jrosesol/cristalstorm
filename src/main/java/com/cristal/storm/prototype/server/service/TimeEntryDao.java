package com.cristal.storm.prototype.server.service;

import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.server.domain.Account;
import com.cristal.storm.prototype.server.domain.Activity;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.server.domain.TimeEntry;
import com.googlecode.objectify.Key;

public class TimeEntryDao extends ObjectifyDao<TimeEntry> {

    ///////////////////////////////////////////////////////////////////////////
    // Override
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public List<TimeEntry> listAll() {
        return this.listAllForUser();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    public void saveTimeEntry(TimeEntry timeEntry, Account account, Activity activity) {
        // Set the time entry owner (this is the logged user) 
        AppUser loggedInUser = LoginService.getLoggedInUser();
        timeEntry.setOwningUser(loggedInUser);
        
        // Set the Time Entry properties 
        timeEntry.setOwningAccount(account);
        timeEntry.setOwningActivity(activity);
        
        // Persist the time entry
        this.put(timeEntry);
    }
    
    public TimeEntry saveTimeEntryAndReturn(TimeEntry timeEntry, Account account, Activity activity) {
        // Persist the time entry
        saveTimeEntry(timeEntry, account, activity);
        
        try {
            return timeEntry;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        
    public List<TimeEntry> readInRangeTimeEntries(Date fromTime, Date thruTime) {
        return this.listAllInRangeForUser(fromTime, thruTime);
    }
    
    public void deleteTimeEntry(TimeEntry timeEntry) {
        this.delete(timeEntry);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
