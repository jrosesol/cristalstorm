/**
 *
 *
 * @author Jose Rose
 * 2011-08-11
 */
package com.cristal.storm.prototype.client.i18n;

import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
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
    
    public static String getTimeCodeValue(Long timeCode, Map<Long, String> domainTimeCodeMap) {
        String timeCodeValue = "";
        int integerValer = (int) timeCode.longValue();
        switch (integerValer) {
            case (int) DomainTimeCodesProxy.NORMAL:
                timeCodeValue = lConstants.normalTimeCode();
                break;
            case (int) DomainTimeCodesProxy.PAID_VACATION:
                timeCodeValue = lConstants.vacationTimeCode();
                break;
            case (int) DomainTimeCodesProxy.SICKNESS:
                timeCodeValue = lConstants.sicknessTimeCode();
                break;
            case (int) DomainTimeCodesProxy.HOLIDAY:
                timeCodeValue = lConstants.holidayTimeCode();
                break;
            case (int) DomainTimeCodesProxy.LUNCH:
                timeCodeValue = lConstants.lunchTimeCode();
                break;
            case (int) DomainTimeCodesProxy.EXTENDED:
                Log.fatal("WHY DID WE STORE THIS VALUE????");
                break;
            default:
                // This one does not support multi-language as it is user defined. 
                timeCodeValue = domainTimeCodeMap.get(timeCode);
                break;
        }
        
        return timeCodeValue;
    }
    
    public static String getTimeCodeValue(TimeEntryProxy timeEntryProxy, Map<Long, String> domainTimeCodeMap) {
        return getTimeCodeValue(timeEntryProxy.getTimeCode(), domainTimeCodeMap);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
