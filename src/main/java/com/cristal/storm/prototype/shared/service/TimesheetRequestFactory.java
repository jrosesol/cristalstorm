package com.cristal.storm.prototype.shared.service;

import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.server.locator.DaoServiceLocator;
import com.cristal.storm.prototype.server.service.AccountDao;
import com.cristal.storm.prototype.server.service.ActivityDao;
import com.cristal.storm.prototype.server.service.DomainDao;
import com.cristal.storm.prototype.server.service.DomainTimeCodesDao;
import com.cristal.storm.prototype.server.service.TimeEntryDao;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.DomainProxy;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

public interface TimesheetRequestFactory extends RequestFactory {
    /**
     * Service stub for methods in ActivityDao
     * 
     * TODO Enhance RequestFactory to enable service stubs to extend a base
     * interface so we don't have to repeat methods from the base ObjectifyDao
     * in each stub
     */
    @Service(value = ActivityDao.class, locator = DaoServiceLocator.class)
    interface ActivityListRequestContext extends RequestContext {
        Request<List<ActivityProxy>> listAll();
        Request<Void> save(ActivityProxy newActivity, AccountProxy account);
        Request<ActivityProxy> saveActivityAndReturn(ActivityProxy newActivity, AccountProxy account);
    }

    ActivityListRequestContext activityListRequest();
    
    /**
     * Service stub for methods in TimeEntryDao
     * 
     * TODO Enhance RequestFactory to enable service stubs to extend a base
     * interface so we don't have to repeat methods from the base ObjectifyDao
     * in each stub
     */
    @Service(value = TimeEntryDao.class, locator = DaoServiceLocator.class)
    interface TimeEntryRequestContext extends RequestContext {
        Request<List<TimeEntryProxy>> listAll();        
        Request<Void> saveTimeEntry(TimeEntryProxy timeEntry, AccountProxy account, ActivityProxy activity);
        Request<TimeEntryProxy> saveTimeEntryAndReturn(TimeEntryProxy timeEntry, AccountProxy account, ActivityProxy activity);
        Request<List<TimeEntryProxy>> readInRangeTimeEntries(Date fromTime, Date thruTime);
        Request<Void> deleteTimeEntry(TimeEntryProxy timeEntry);
    }
    
    TimeEntryRequestContext timeEntryRequest();
    
    /**
     * Service stub for methods in TimeEntryDao
     * 
     * TODO Enhance RequestFactory to enable service stubs to extend a base
     * interface so we don't have to repeat methods from the base ObjectifyDao
     * in each stub
     */
    @Service(value = AccountDao.class, locator = DaoServiceLocator.class)
    interface AccountRequestContext extends RequestContext {
        Request<List<AccountProxy>> listAll();
        Request<Void> saveAccount(AccountProxy account);
        Request<AccountProxy> saveAccountAndReturn(AccountProxy account);
        Request<Void> deleteAccount(AccountProxy account);
    }
    AccountRequestContext accountRequest();
    
    
    /**
     * Service stub for methods in DomainDao
     * 
     * TODO Enhance RequestFactory to enable service stubs to extend a base
     * interface so we don't have to repeat methods from the base ObjectifyDao
     * in each stub
     */
    @Service(value = DomainDao.class, locator = DaoServiceLocator.class)
    interface DomainRequestContext extends RequestContext {
        Request<DomainProxy> registerDomain(DomainProxy newDomain);
    }
    DomainRequestContext domainRequest();
    
    /**
     * Service stub for methods in DomainTimeCodesDao
     * 
     * TODO Enhance RequestFactory to enable service stubs to extend a base
     * interface so we don't have to repeat methods from the base ObjectifyDao
     * in each stub
     */
    @Service(value = DomainTimeCodesDao.class, locator = DaoServiceLocator.class)
    interface DomainTimeCodeRequestContext extends RequestContext {
        Request<List<DomainTimeCodesProxy>> listAll();
        Request<DomainTimeCodesProxy> addDomainTimeCodeAndReturn1(TimeCodeType timeCodeType, String timeCodeValue);
        Request<DomainTimeCodesProxy> addDomainTimeCodeAndReturn2(TimeCodeType timeCodeType);
    }
    DomainTimeCodeRequestContext domainTimeCodeRequest();
}
