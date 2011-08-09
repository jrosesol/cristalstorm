package com.cristal.storm.prototype.server.service;

import java.util.List;

import com.cristal.storm.prototype.server.domain.Account;
import com.cristal.storm.prototype.server.domain.Activity;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.googlecode.objectify.Key;

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
