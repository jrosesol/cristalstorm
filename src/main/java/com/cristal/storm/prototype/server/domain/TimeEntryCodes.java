/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.server.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.client.util.UtilFunc;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.googlecode.objectify.annotation.Entity;


/**
 * TODO: Add comments for TimeEntryCodes
 *
 */
@Entity
public class TimeEntryCodes {
   
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public List<Long> timeCodes;
    public List<String> timeCodeValues;
    
    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    public TimeEntryCodes() {
        timeCodes = new ArrayList<Long>();
        timeCodeValues = new ArrayList<String>();
    }
    
    public void initTimeCodes() {
        timeCodes.add(DomainTimeCodesProxy.NORMAL);
        timeCodes.add(DomainTimeCodesProxy.PAID_VACATION);
        timeCodes.add(DomainTimeCodesProxy.SICKNESS);
        timeCodes.add(DomainTimeCodesProxy.HOLIDAY);
        timeCodes.add(DomainTimeCodesProxy.LUNCH);
        
        timeCodeValues.add("NORMAL");
        timeCodeValues.add("PAID_VACATION");
        timeCodeValues.add("SICKNESS");
        timeCodeValues.add("HOLIDAY");
        timeCodeValues.add("LUNCH");
    }
        
    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    
    public List<Long> getTimeCodes() {
        return timeCodes;
    }
    
    public List<String> getTimeCodeValues() {
        return timeCodeValues;
    }
   
    public void addTimeCode(Long timeCode, String timeCodeValue) {
        if (timeCodes.contains(timeCode)) {
            Log.fatal("No reapeted code should be here!");
        }
        
        timeCodes.add(timeCode);
        timeCodeValues.add(timeCodeValue);
    }
}
