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
import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.DomainProxy;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.AccountRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.ActivityListRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.DomainRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.DomainTimeCodeRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

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
    public static String DOMAIN_NAME = "domain24.com";

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
        newDomain.setName(DOMAIN_NAME);
        domainCtx.registerDomain(newDomain).fire(new Receiver<DomainProxy>() {

            @Override
            public void onSuccess(DomainProxy response) {
                Log.info("Domain : " + response.getDescription());
                
                // Check if we have some data registered for current user
                AccountRequestContext accountCheckCtx = rf.accountRequest();
                accountCheckCtx.listAll().fire(new Receiver<List<AccountProxy>>() {

                    @Override
                    public void onSuccess(List<AccountProxy> response) {
                                                
                        if (response.size() > 0) {
                            Log.info("DEMO DATA IS ALREADY REGISTERED");
                            return;   
                        }
                        else {
                            // Try to register time codes for domain
                            registerTimeCode(TimeEntryCode.TimeCodeType.NORMAL);
                            registerTimeCode(TimeEntryCode.TimeCodeType.HOLIDAY);
                            registerTimeCode(TimeEntryCode.TimeCodeType.LUNCH);
                            registerTimeCode(TimeEntryCode.TimeCodeType.PAID_VACATION);
                            registerTimeCode(TimeEntryCode.TimeCodeType.SICKNESS);
                            registerTimeCodeExtended(TimeEntryCode.TimeCodeType.EXTENDED, "Super Thing To Do Code");                            
                            
                            // Load the default data
                            loadDataFromDataStore();
                        }
                    }
                });
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                // TODO Auto-generated method stub
                super.onFailure(error);
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

                saveTimeEntry(owningAccount, response, 0, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, 1, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, 2, TimeEntryCode.TimeCodeType.PAID_VACATION);
                saveTimeEntry(owningAccount, response, 3, TimeEntryCode.TimeCodeType.HOLIDAY);
                saveTimeEntry(owningAccount, response, -1, TimeEntryCode.TimeCodeType.SICKNESS);
                saveTimeEntry(owningAccount, response, -2, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, -3, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, -4, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, -5, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, -6, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, -7, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, -8, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, -9, TimeEntryCode.TimeCodeType.NORMAL);
                saveTimeEntry(owningAccount, response, -10, TimeEntryCode.TimeCodeType.NORMAL);
            }
        });
    }

    private void saveTimeEntry(final AccountProxy owningAccount, ActivityProxy response, int dayOffset, TimeCodeType timeCode) {
        // Save dummy time entries
        Random randomGenerator = new Random();

        TimeEntryRequestContext reqCtx = rf.timeEntryRequest();
        final TimeEntryProxy newTimeEntry = reqCtx.create(TimeEntryProxy.class);
        newTimeEntry.setSpentTime(randomGenerator.nextInt(100));
        Date currentTime = new Date(System.currentTimeMillis());
        CalendarUtil.addDaysToDate(currentTime, dayOffset);
        newTimeEntry.setTimeEntryTimestamp(currentTime);
        newTimeEntry.setTimeCode(timeCode);

        reqCtx.saveTimeEntryAndReturn(newTimeEntry, owningAccount, response).fire(new Receiver<TimeEntryProxy>() {

            @Override
            public void onSuccess(TimeEntryProxy response) {
                Log.info("Time Entry was created: " + response.getDescription());
            }
        });
    }

    private void registerTimeCode(TimeCodeType timeCode) {
        DomainTimeCodeRequestContext domainTimeCodesCtx = rf.domainTimeCodeRequest();
        domainTimeCodesCtx.addDomainTimeCodeAndReturn2(timeCode).fire(new Receiver<DomainTimeCodesProxy>() {

            @Override
            public void onSuccess(DomainTimeCodesProxy response) {
                Log.info("DomainTimeCodes : " + response.getDescription());
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                Log.info("registerTimeCode FAILED : " + error.getMessage());
            }    
        });
    }
    
    private void registerTimeCodeExtended(TimeCodeType timeCode, String timeCodeValue) {
        DomainTimeCodeRequestContext domainTimeCodesCtx = rf.domainTimeCodeRequest();
        domainTimeCodesCtx.addDomainTimeCodeAndReturn1(timeCode, timeCodeValue).fire(new Receiver<DomainTimeCodesProxy>() {

            @Override
            public void onSuccess(DomainTimeCodesProxy response) {
                Log.info("DomainTimeCodes : " + response.getDescription());
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                Log.info("registerTimeCodeExtended FAILED : " + error.getMessage());
            }            
        });
    }

}
