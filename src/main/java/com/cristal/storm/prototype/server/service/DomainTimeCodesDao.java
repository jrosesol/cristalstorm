package com.cristal.storm.prototype.server.service;

import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.client.util.UtilFunc;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.server.domain.Domain;
import com.cristal.storm.prototype.server.domain.DomainTimeCodes;
import com.cristal.storm.prototype.server.domain.TimeEntryCodes;
import com.cristal.storm.prototype.shared.TooManyResultsException;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.web.bindery.requestfactory.shared.Request;
import com.googlecode.objectify.Key;

public class DomainTimeCodesDao extends ObjectifyDao<DomainTimeCodes> {
    
    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    
    private DomainTimeCodes getTimeCodesInternal(Domain domain) {
        DomainTimeCodes domainTimeCodes = null;
        try {            
            domainTimeCodes = getByProperty(DomainTimeCodes.NAME_FIELD_NAME, domain.getName());
        } catch (TooManyResultsException e) {
            e.printStackTrace();
        }

        return domainTimeCodes;
    }
    
    public DomainTimeCodes getTimeCodes() {
        Domain curDomain = getCurrentDomain();
        DomainTimeCodes domainTimeCodes = null;
        try {            
            domainTimeCodes = getByProperty(DomainTimeCodes.NAME_FIELD_NAME, curDomain.getName());
        } catch (TooManyResultsException e) {
            e.printStackTrace();
        }

        return domainTimeCodes;
    }

    public DomainTimeCodes addDomainTimeCodeAndReturn(String timeCodeValue) {

        Domain curDomain = getCurrentDomain();
        
        // Get the domain time code        
        DomainTimeCodes curDomainTimeCodes = getTimeCodesInternal(curDomain);
        
        // It might be the first so it's empty
        if (curDomainTimeCodes == null) {
            curDomainTimeCodes = new DomainTimeCodes();
            curDomainTimeCodes.setDomain(curDomain);
            curDomainTimeCodes.initTimeCodes();
        }
        
        //
        curDomainTimeCodes.setTimeCode(UtilFunc.generateUID(), timeCodeValue);
        
        // Save the state
        this.put(curDomainTimeCodes);
        
        return curDomainTimeCodes;
    }

    /**
     * @throws TooManyResultsException
     */
    protected Domain getCurrentDomain() {
        AppUser loggedInUser = LoginService.getLoggedInUser();
        String userDomainName = AppUserDao.getDomain(loggedInUser.getEmail());

        Domain domain = null;
        DomainDao domainDao = new DomainDao();
        try {
            domain = domainDao.getByProperty(Domain.NAME_FIELD_NAME, userDomainName);
        } catch (TooManyResultsException e) {
            e.printStackTrace();
        }
        
        return domain;
    }
}
