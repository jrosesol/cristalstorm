package com.cristal.storm.prototype.server.service;

import java.security.InvalidParameterException;
import java.util.StringTokenizer;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.server.domain.Domain;
import com.cristal.storm.prototype.shared.TooManyResultsException;

public class AppUserDao extends ObjectifyDao<AppUser> {
    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    
    public AppUser registerUser(AppUser user) {
        AppUser loggedInUser = LoginService.getLoggedInUser();
        
        // Strip the domain from the email
        StringTokenizer token = new StringTokenizer(loggedInUser.getEmail(), "@");
        String userDomainName = token.nextToken();
        
        // We have to determine the user domain from the user credentials
        // For Google App users this comes from directly from the user
        // account. The email can tell us the user is registered at the
        // requested domain.
        
        Domain userDomain = null;
        try {
            DomainDao userDomainDoa = new DomainDao();
            // Query user domain
            userDomain = userDomainDoa.getByProperty(Domain.NAME_FIELD_NAME, userDomainName);
        } catch (TooManyResultsException e) {
            throw new RuntimeException(e);
        }

        // TODO: Check this, should be done when the app is first registered for a domain
        // THIS should not happen ever!
        if (userDomain == null) {
            Log.fatal("The domain should have been registered!!!");
            throw new RuntimeException(new InvalidParameterException("The domain should have been registered!!!"));
        }
        user.setDomain(userDomain);
        
        // Now save the user
        this.put(user);
        try {
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}