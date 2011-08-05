package com.cristal.storm.prototype.server.service;

import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.server.domain.Account;
import com.cristal.storm.prototype.server.domain.Activity;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.server.domain.TimeEntry;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.google.web.bindery.requestfactory.shared.Request;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class ActivityDao extends ObjectifyDao<Activity> {
    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    public List<Activity> listAll() {
        return this.listAllForUser();
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Wraps put() so as not to return a Key, which RF can't handle
     * 
     * @param obj
     */
    public void save(Activity activity, Account account) {
        AppUser loggedInUser = LoginService.getLoggedInUser();
        activity.setOwner(loggedInUser);
        activity.setOwningAccount(account);
        
//        // NOCOMMIT
//        // TEST //
//        // Do some tests
//        
//        ObjectifyService.register(Account.class);
//        ObjectifyService.register(TimeEntry.class);
//        
//        // Create an Account
//        Account anAccount = new Account();
//        anAccount.setName("Sample Account");
//        anAccount.addOwner(loggedInUser);
//        ofy().put(anAccount);
//        
//        // Create an activity
//        Activity anActivity = new Activity();
//        anActivity.setName("Sample Activity");
//        anActivity.setOwner(loggedInUser);
//        anActivity.setOwningAccount(anAccount);
//        ofy().put(anActivity);
//        
//        // Create a TimeEntry
//        TimeEntry aTimeEntry = new TimeEntry();
//        aTimeEntry.setSpentTime(5.5);
//        aTimeEntry.setTimeEntryTimestamp(new Date(2011, 7, 22));
//        aTimeEntry.setOwningUser(loggedInUser);
//        aTimeEntry.setOwningActivity(anActivity);
//        aTimeEntry.setOwningAccount(anAccount);
//        Key<TimeEntry> timeEntry = ofy().put(aTimeEntry);
//        
//        // Get all the data back!
//        TimeEntry savedTimeEntry = ofy().get(timeEntry);
//        Activity owningActivity  = ofy().get(savedTimeEntry.getOwningActivity());
//        Account owningAccount    = ofy().get(savedTimeEntry.getOwningAccount());
//        System.out.print(savedTimeEntry.getSpentTime()  + "\n");
//        System.out.print(owningActivity.getName()       + "\n");
//        System.out.print(owningAccount.getName()        + "\n");
//        
//        //////////////////////
        
        this.put(activity);
    }

    public Activity saveActivityAndReturn(Activity activity, Account account) {
        AppUser loggedInUser = LoginService.getLoggedInUser();
        activity.setOwner(loggedInUser);
        Key<Activity> key = this.put(activity);
        try {
            return activity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Remove an activity.
     *
     * @param list
     */
    public void removeActivity(Activity activity) {
        this.delete(activity);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}
