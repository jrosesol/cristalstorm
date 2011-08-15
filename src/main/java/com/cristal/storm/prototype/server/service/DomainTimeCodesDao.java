package com.cristal.storm.prototype.server.service;

import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.server.domain.DomainTimeCodes;
import com.cristal.storm.prototype.shared.TooManyResultsException;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
import com.google.web.bindery.requestfactory.shared.Request;

public class DomainTimeCodesDao extends ObjectifyDao<DomainTimeCodes> {
    
    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    
    @Override 
    public List<DomainTimeCodes> listAll() {
        List<DomainTimeCodes> domainTimeCode = new ArrayList<DomainTimeCodes>();
        
        DomainTimeCodes timeCodes = listTimeCodes();
        
        if (timeCodes != null) {
            domainTimeCode.add(timeCodes);
            return domainTimeCode;
        }
        else
            return null;
    }

    public DomainTimeCodes listTimeCodes() {
        AppUser loggedInUser = LoginService.getLoggedInUser();

        String userDomainName = AppUserDao.getDomain(loggedInUser.getEmail());
        DomainTimeCodes domainTimeCodes = null;
        try {
            DomainDao domainDao = new DomainDao();
            domainTimeCodes = domainDao.getByProperty(DomainTimeCodes.DOMAIN_FIELD_NAME, userDomainName);
        } catch (TooManyResultsException e) {
            e.printStackTrace();
        }

        return domainTimeCodes;
    }

    public DomainTimeCodes addDomainTimeCodeAndReturn1(TimeCodeType timeCodeType, String timeCodeValue) {
        // Get the domain time code        
        DomainTimeCodes curDomainTimeCodes = listTimeCodes();
        
        // It might be the first so it's empty
        if (curDomainTimeCodes == null)
            curDomainTimeCodes = new DomainTimeCodes();
        
        //
        curDomainTimeCodes.addTimeCode(timeCodeType, timeCodeValue);
        
        // Save the state
        this.put(curDomainTimeCodes);
        
        return curDomainTimeCodes;
    }
    
    public DomainTimeCodes addDomainTimeCodeAndReturn2(TimeCodeType timeCodeType) {
        // Get the domain time code        
        DomainTimeCodes curDomainTimeCodes = listTimeCodes();
        
        // It might be the first so it's empty
        if (curDomainTimeCodes == null)
            curDomainTimeCodes = new DomainTimeCodes();
        
        //
        curDomainTimeCodes.addTimeCode(timeCodeType);
        
        // Save the state
        this.put(curDomainTimeCodes);
        
        return curDomainTimeCodes;
    }

}
