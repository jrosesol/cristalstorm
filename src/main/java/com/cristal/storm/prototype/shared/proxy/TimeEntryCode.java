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
        this.timeEntryValue = getTimeCodeValue(timeCode);
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
    protected String getTimeCodeValue(TimeCodeType timeCodeType) {

        
        String timeCodeValue = "";
        switch (timeCodeType) {
//            case NORMAL:
//                timeCodeValue = appCteProvider.get().normalTimeCode();
//                break;
//            case PAID_VACATION:
//                timeCodeValue = appCteProvider.get().vacationTimeCode();
//                break;
//            case SICKNESS:
//                timeCodeValue = appCteProvider.get().sicknessTimeCode();
//                break;
//            case HOLIDAY:
//                timeCodeValue = appCteProvider.get().holidayTimeCode();
//                break;
//            case LUNCH:
//                timeCodeValue = appCteProvider.get().lunchTimeCode();
//                break;
            case EXTENDED:
                timeCodeValue = getTimeEntryValue();
                break;
            default:
                Log.fatal("WHAT OTHER CASE?");
                break;
        }
        
        return timeCodeValue;
    }

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
