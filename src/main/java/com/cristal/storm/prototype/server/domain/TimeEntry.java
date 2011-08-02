package com.cristal.storm.prototype.server.domain;

import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.server.service.AppUserDao;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class TimeEntry extends DatastoreObject {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    private Date timeEntryTimestamp;
    private double spentTime;
    
    // Keys
    private Key<AppUser>  owningUser;
    private Key<Activity> owningActivity;
    private Key<Account>  owningAccount;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////

    public TimeEntry() {
        
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "[timeEntryTimestamp : " + getTimeEntryTimestamp() + ", spentTime : " + getSpentTime() + ", id: "
                + getId() + ", owningUser : " + getOwningUser() + ", owningActivity : " + getOwningActivity()
                + ", owningAccount : " + getOwningAccount() + "]";
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    
    public String getString() {
        return this.toString();
    }
    
    public void setTimeEntryTimestamp(Date timeEntryTimestamp) {
        this.timeEntryTimestamp = timeEntryTimestamp;
    }

    public Date getTimeEntryTimestamp() {
        return timeEntryTimestamp;
    }

    public void setSpentTime(double spentTime) {
        this.spentTime = spentTime;
    }

    public double getSpentTime() {
        return spentTime;
    }

    public void setOwningUser(AppUser owningUser) {
        this.owningUser = new AppUserDao().key(owningUser);
    }

    public Key<AppUser> getOwningUser() {
        return owningUser;
    }

    public void setOwningActivity(Activity owningActivity) {
        this.owningActivity = new Key<Activity>(Activity.class, owningActivity.getId());
    }

    public Key<Activity> getOwningActivity() {
        return owningActivity;
    }

    public void setOwningAccount(Account owningAccount) {
        this.owningAccount = new Key<Account>(Account.class, owningAccount.getId());
    }
    
    public Key<Account> getOwningAccount() {
        return owningAccount;
    }
    
    public long getOwningUserId() {
        return getOwningUser().getId();
    }
    
    public long getOwningActivityId() {
        return getOwningActivity().getId();
    }
    
    public long getOwningAccountId() {
        return getOwningAccount().getId();
    }
}