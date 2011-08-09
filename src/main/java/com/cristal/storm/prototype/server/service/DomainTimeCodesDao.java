package com.cristal.storm.prototype.server.service;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.server.domain.DomainTimeCodes;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode;
import com.google.web.bindery.requestfactory.shared.Request;

public class DomainTimeCodesDao extends ObjectifyDao<DomainTimeCodes> {
    
    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    

    public DomainTimeCodes listTimeCodes() {
        AppUser loggedInUser = LoginService.getLoggedInUser();
        
        List<DomainTimeCodes> domainTimeCodes = listByProperty(DomainTimeCodes.DOMAIN_FIELD_NAME, loggedInUser.getDomain());
        
        if (domainTimeCodes.size() > 1) {
            Log.warn("Why are they more then 1 timeCode list for the same domain?");
        }
        
        if (domainTimeCodes.size() == 1) {
            return domainTimeCodes.get(0);
        }
        else {
            return null;
        }
    }
    
    DomainTimeCodes addDomainTimeCodeAndReturn(TimeEntryCode timeEntryCode) {
        // Get the domain time code        
        DomainTimeCodes curDomainTimeCodes = listTimeCodes();
        
        //
        curDomainTimeCodes.addTimeEntryCode(timeEntryCode);
        
        // Save the state
        this.put(curDomainTimeCodes);
        
        return curDomainTimeCodes;
    }
}
