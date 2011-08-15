/**
 *
 *
 * @author Jose Rose
 * 2011-08-11
 */
package com.cristal.storm.prototype.client.i18n;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.google.gwt.core.client.GWT;

/**
 * TODO: Add comments for UtilFunc
 *
 */
public class UtilFunc {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////

    static AppsConstants lConstants = (AppsConstants) GWT.create(AppsConstants.class);
    
    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    public static String getTimeCodeValue(TimeCodeType timeCode) {
        String timeCodeValue = "";
        switch (timeCode) {
            case NORMAL:
                timeCodeValue = lConstants.normalTimeCode();
                break;
            case PAID_VACATION:
                timeCodeValue = lConstants.vacationTimeCode();
                break;
            case SICKNESS:
                timeCodeValue = lConstants.sicknessTimeCode();
                break;
            case HOLIDAY:
                timeCodeValue = lConstants.holidayTimeCode();
                break;
            case LUNCH:
                timeCodeValue = lConstants.lunchTimeCode();
                break;
            case EXTENDED:
                timeCodeValue = "FIND A WAY TO GET USER DEFINED";
                break;
            default:
                Log.fatal("WHAT OTHER CASE?");
                break;
        }
        
        return timeCodeValue;
    }
    
    public static String getTimeCodeValue(TimeEntryProxy timeEntryProxy) {
        if (timeEntryProxy.getTimeCode() == TimeCodeType.EXTENDED) {
            return timeEntryProxy.getTimeCodeValue();
        }
        else {
            return getTimeCodeValue(timeEntryProxy.getTimeCode());
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
