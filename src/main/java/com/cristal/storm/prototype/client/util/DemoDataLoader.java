/**
 *
 *
 * @author Jose Rose
 * 2011-07-29
 */
package com.cristal.storm.prototype.client.util;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.DomainProxy;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.AccountRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.ActivityListRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.DomainRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.DomainTimeCodeRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Create demo data to populate the server
 * 
 * We have a problem with Key<> serialization, please read-on:
 * http://groups.google
 * .com/group/objectify-appengine/browse_thread/thread/880a2c107a7c71d8
 * 
 */
public class DemoDataLoader {

    private final TimesheetRequestFactory rf;
    
    /* Has to be the same as in LoginSerce.java */
    public static String domainName = "domain.com";

    @Inject
    public DemoDataLoader(final TimesheetRequestFactory rf) {
        this.rf = rf;
    }
    
    public void loadDemoData() {
        // FIXCOMMIT //
        /*
         * Here we will create dummy accounts and activities for the current
         * dummy user.
         */
        
        // Try to register the domain
        DomainRequestContext domainCtx = rf.domainRequest();
        DomainProxy newDomain = domainCtx.create(DomainProxy.class);
        domainCtx.registerDomain(newDomain).fire(new Receiver<DomainProxy>() {

            @Override
            public void onSuccess(DomainProxy response) {
                Log.info("Domain : " + response.getDescription());
            }
        });
        
        // Try to register time codes for domain
//        DomainTimeCodeRequestContext domainTimeCodesCtx = rf.domainTimeCodeRequest();
//        domainTimeCodesCtx.addDomainTimeCodeAndReturn(new TimeEntryCode(TimeEntryCode.TimeCodeType.NORMAL)).fire(new Receiver<DomainTimeCodesProxy>() {
//
//            @Override
//            public void onSuccess(DomainTimeCodesProxy response) {
//                Log.info("DomainTimeCodes : " + response.getDescription());
//            }
//        });
        
        
        // Check if we have some data registered for current user
        AccountRequestContext accountCheckCtx = rf.accountRequest();
        accountCheckCtx.listAll().fire(new Receiver<List<AccountProxy>>() {

            @Override
            public void onSuccess(List<AccountProxy> response) {
                
                Log.info("DEMO DATA IS ALREADY REGISTERED");
                
                if (response.size() > 0)
                    return;
                else
                    loadDataFromDataStore();
            }
        });
    }

    private void loadDataFromDataStore() {
        // Save a dummy account
        AccountRequestContext accountCtx = rf.accountRequest();
        final AccountProxy testAccountProxy1 = accountCtx.create(AccountProxy.class);
        testAccountProxy1.setName("Demo Project 1");
        accountCtx.saveAccountAndReturn(testAccountProxy1).fire(new Receiver<AccountProxy>() {

            @Override
            public void onSuccess(AccountProxy response) {
                // Save a dummy activity
                createActivity("Something todo", response);
                
                Log.info("Account was created: " + response.getDescription());
            }
        });

        accountCtx = rf.accountRequest();
        final AccountProxy testAccountProxy2 = accountCtx.create(AccountProxy.class);
        testAccountProxy2.setName("Demo Project 2");
        accountCtx.saveAccountAndReturn(testAccountProxy2).fire(new Receiver<AccountProxy>() {

            @Override
            public void onSuccess(AccountProxy response) {
                // Save a dummy activity
                createActivity("Some activity", response);
                
                Log.info("Account was created: " + response.getDescription());
            }
        });
    }

    private void createActivity(String projectName, final AccountProxy owningAccount) {
        ActivityListRequestContext activityCtx = rf.activityListRequest();
        final ActivityProxy testActivityProxy = activityCtx.create(ActivityProxy.class);
        testActivityProxy.setName(projectName);
        activityCtx.saveActivityAndReturn(testActivityProxy, owningAccount).fire(new Receiver<ActivityProxy>() {

            @Override
            public void onSuccess(ActivityProxy response) {

                saveTimeEntry(owningAccount, response, 0);
                saveTimeEntry(owningAccount, response, 1);
                saveTimeEntry(owningAccount, response, 2);
                saveTimeEntry(owningAccount, response, 3);
                saveTimeEntry(owningAccount, response, -1);
                saveTimeEntry(owningAccount, response, -2);
                saveTimeEntry(owningAccount, response, -3);
                saveTimeEntry(owningAccount, response, -4);
                saveTimeEntry(owningAccount, response, -5);
                saveTimeEntry(owningAccount, response, -6);
                saveTimeEntry(owningAccount, response, -7);
                saveTimeEntry(owningAccount, response, -8);
                saveTimeEntry(owningAccount, response, -9);
                saveTimeEntry(owningAccount, response, -10);
            }
        });
    }

    private void saveTimeEntry(final AccountProxy owningAccount, ActivityProxy response, int dayOffset) {
        // Save dummy time entries
        Random randomGenerator = new Random();

        TimeEntryRequestContext reqCtx = rf.timeEntryRequest();
        final TimeEntryProxy newTimeEntry = reqCtx.create(TimeEntryProxy.class);
        newTimeEntry.setSpentTime(randomGenerator.nextInt(100));
        Date currentTime = new Date(System.currentTimeMillis());
        CalendarUtil.addDaysToDate(currentTime, dayOffset);
        newTimeEntry.setTimeEntryTimestamp(currentTime);

        reqCtx.saveTimeEntryAndReturn(newTimeEntry, owningAccount, response).fire(new Receiver<TimeEntryProxy>() {

            @Override
            public void onSuccess(TimeEntryProxy response) {
                Log.info("Time Entry was created: " + response.getDescription());
            }
        });
    }

}
