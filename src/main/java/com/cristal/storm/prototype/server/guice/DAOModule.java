package com.cristal.storm.prototype.server.guice;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.server.domain.Account;
import com.cristal.storm.prototype.server.domain.Activity;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.server.domain.TimeEntry;
import com.google.inject.AbstractModule;
import com.googlecode.objectify.ObjectifyService;

public class DAOModule extends AbstractModule {

    @Override
    protected void configure() {
        ObjectifyService.register(AppUser.class);
        ObjectifyService.register(Activity.class);
        ObjectifyService.register(Account.class);
        ObjectifyService.register(TimeEntry.class);
        
        Log.setCurrentLogLevel(Log.LOG_LEVEL_ERROR);
    }
}
