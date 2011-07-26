package com.cristal.storm.prototype.junit;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.cristal.storm.prototype.server.domain.Account;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.AccountRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.ActivityListRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

public class RequestFactoryTest extends TestCase {
    
    private TimesheetRequestFactory rf;
    private Date testDate;
    private AccountProxy testAccountProxy;
    private ActivityProxy testActivityProxy;
    private TimeEntryProxy newTimeEntry = null;
    
    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    /**
     * Create the test case
     * 
     * @param testName
     *            name of the test case
     */
    public RequestFactoryTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(RequestFactoryTest.class);
    }
    
    @Before 
    public void setUp() throws Exception {
        helper.setUp();
        rf = AppRequestFactoryProvider.get();
        testDate = new Date(System.currentTimeMillis());
        
        // Save a dummy account
        AccountRequestContext accountCtx = rf.accountRequest();
        testAccountProxy  = accountCtx.create(AccountProxy.class);
        AccountProxy mutableObject = accountCtx.edit(testAccountProxy);
        mutableObject.setName("Demo Project");
        Boolean changed = accountCtx.isChanged();
        accountCtx.saveAccount(mutableObject).fire(new Receiver<Void>() {

            @Override
            public void onSuccess(Void response) {
                System.out.print("RequestFactory seems to be working!\n");
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                System.out.print("fail");
            }
        });
      
        ActivityListRequestContext activityCtx = rf.activityListRequest();        
        testActivityProxy = activityCtx.create(ActivityProxy.class);
        testActivityProxy.setName("Demo task");
        activityCtx.save(testActivityProxy).fire(new Receiver<Void>() {

            @Override
            public void onSuccess(Void response) {
                System.out.print("RequestFactory seems to be working!\n");
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                System.out.print("fail");
            }
        });
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    /**
     * Test a time entry save/list request.
     * - Persist a Time Entry.
     * - Get it back an verify it is the same.
     */
    public void testTimeEntryRequests() {

        // Save the time entry

        final TimeEntryRequestContext reqCtx = rf.timeEntryRequest();
        newTimeEntry = reqCtx.create(TimeEntryProxy.class);        
        newTimeEntry.setSpentTime(10.0);
        newTimeEntry.setTimeEntryTimestamp(testDate);

        reqCtx.saveTimeEntry(newTimeEntry, testAccountProxy, testActivityProxy).fire(new Receiver<Void>() {

            @Override
            public void onSuccess(Void response) {
                System.out.print("RequestFactory seems to be working!\n");
            }

            @Override
            public void onFailure(ServerFailure error) {
                System.out.print("saveTimeEntry failed");
            }
        });
        
        // Get the time entry
        
        final TimeEntryRequestContext reqCtxSave = rf.timeEntryRequest();        
        reqCtxSave.listAll().fire(new Receiver<List<TimeEntryProxy>>() {

            @Override
            public void onSuccess(List<TimeEntryProxy> response) {
                
                // Compare the result and initial object
                assertEquals(newTimeEntry.getSpentTime(), response.get(0).getSpentTime());
                assertEquals(newTimeEntry.getTimeEntryTimestamp(), response.get(0).getTimeEntryTimestamp());
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                System.out.print("listAll failed");
            }
        });        
    }
    
    /**
     * Test a time entry get in range request.
     * - Persist a Time Entry.
     * - Get it back an verify it is the same.
     */
    public void testTimeEntryDateRangeRequests() {
        
        // Create two dates (start / end)
        final Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.set(1990, 1, 1, 15, 15, 15);
        final Date startDate = new Date(startDateCalendar.getTimeInMillis());
        
        final Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.set(1999, 1, 1, 15, 15, 15);
        final Date endDate = new Date(endDateCalendar.getTimeInMillis());

        // Save the time entry with time NOW
        saveTimeEntry(10.0, testDate);        
        
        // Save Time Entry with start time
        saveTimeEntry(20.0, startDate);
        
        // Save Time Entry with end time
        saveTimeEntry(30.0, endDate);        
        
        // Get the time entries      
        rf.timeEntryRequest().readInRangeTimeEntries(startDate, endDate).fire(new Receiver<List<TimeEntryProxy>>() {

            @Override
            public void onSuccess(List<TimeEntryProxy> response) {
                
                // Compare the result and initial object
                assertEquals(response.size(), 2);
                assertEquals(startDate, response.get(0).getTimeEntryTimestamp());
                assertEquals(endDate, response.get(1).getTimeEntryTimestamp());
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                System.out.print("listAll failed");
            }
        });
    }
    
    /**
     * Test a time entry delete
     * - Persist a Time Entry.
     * - Get it back an verify it is the same.
     */
    public void testTimeEntryDeleteRequests() {

        // Save the time entry with time NOW
        saveTimeEntry(10.0, testDate);        
        
        // Get the time entries
        rf.timeEntryRequest().listAll().fire(new Receiver<List<TimeEntryProxy>>() {

            @Override
            public void onSuccess(List<TimeEntryProxy> response) {
                
                assertEquals(response.size(), 1);
                
                // Delete and get again 
                rf.timeEntryRequest().deleteTimeEntry(response.get(0)).fire(new Receiver<Void>() {

                    @Override
                    public void onSuccess(Void response) {
                        
                    }
                    
                    @Override
                    public void onFailure(ServerFailure error) {
                        System.out.print("delete failed");
                    }
                });
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                System.out.print("listAll failed");
            }
        });
        
        rf.timeEntryRequest().listAll().fire(new Receiver<List<TimeEntryProxy>>() {

            @Override
            public void onSuccess(List<TimeEntryProxy> response) {
                
                // Compare the result and initial object
                assertEquals(response.size(), 0);
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                System.out.print("listAll failed");
            }
        });  
    }

    private void saveTimeEntry(double timeSpent, Date timestamp) {
        final TimeEntryRequestContext reqCtx = rf.timeEntryRequest();
        newTimeEntry = reqCtx.create(TimeEntryProxy.class);        
        newTimeEntry.setSpentTime(timeSpent);
        newTimeEntry.setTimeEntryTimestamp(timestamp);

        reqCtx.saveTimeEntry(newTimeEntry, testAccountProxy, testActivityProxy).fire(new Receiver<Void>() {

            @Override
            public void onSuccess(Void response) {
                System.out.print("RequestFactory seems to be working!\n");
            }

            @Override
            public void onFailure(ServerFailure error) {
                System.out.print("saveTimeEntry failed");
            }
        });
    }
}
