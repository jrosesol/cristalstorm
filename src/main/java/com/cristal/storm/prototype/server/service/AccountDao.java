package com.cristal.storm.prototype.server.service;

import java.util.List;

import com.cristal.storm.prototype.server.domain.Account;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.googlecode.objectify.Key;

public class AccountDao extends ObjectifyDao<Account> {
    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    public List<Account> listAll() {
        return this.listAllForUser();
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Wraps put() so as not to return a Key, which RF can't handle
     * 
     * @param obj
     */
    public void saveAccount(Account account) {
        AppUser loggedInUser = LoginService.getLoggedInUser();
        account.addOwner(loggedInUser);
        this.put(account);
    }

    public Account saveAccountAndReturn(Account account) {
        AppUser loggedInUser = LoginService.getLoggedInUser();
        account.addOwner(loggedInUser);
        Key<Account> key = this.put(account);
        try {
            //return this.get(key);
            return account;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Remove an Account.
     *
     * @param list
     */
    public void deleteAccount(Account account) {
        this.delete(account);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////
}