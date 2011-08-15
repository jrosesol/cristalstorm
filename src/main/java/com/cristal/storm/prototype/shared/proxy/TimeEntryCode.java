/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.shared.proxy;

import javax.persistence.Transient;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * TODO: Add comments for TimeEntryCodes
 *
 */
public class TimeEntryCode {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * Time codes for the time entries.
     * <br>
     * <code>NORMAL</code> : is the value for a normal work entry.
     * <br>
     * <code>EXTENDED</code> : is a user defined code which only allows to input time.
     *
     */
    public enum TimeCodeType {NORMAL, PAID_VACATION, SICKNESS, HOLIDAY, LUNCH, EXTENDED};

    private TimeCodeType timeEntryCode;
    private String timeEntryValue;
            
    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public TimeEntryCode() {
        init();
    }
    
    public TimeEntryCode(TimeCodeType timeCode) {
        init();
        this.timeEntryCode = timeCode;
    }
    
    public TimeEntryCode(TimeCodeType timeCode, String timeCodeValue) {
        init();
        this.timeEntryCode = timeCode;
        this.timeEntryValue = timeCodeValue;
    }
    
    private void init() {
        timeEntryValue = "";
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    public void setTimeEntryCode(TimeCodeType timeEntryCode) {
        this.timeEntryCode = timeEntryCode;
    }
    
    public TimeCodeType getTimeEntryCode() {
        return timeEntryCode;
    }
    
    public void setTimeEntryValue(String timeEntryValue) {
        this.timeEntryValue = timeEntryValue;
    }
    
    public String getTimeEntryValue() {
        return timeEntryValue;
    }
}
