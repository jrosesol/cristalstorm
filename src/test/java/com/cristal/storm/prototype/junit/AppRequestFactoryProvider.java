package com.cristal.storm.prototype.junit;

import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.server.ServiceLayer;
import com.google.web.bindery.requestfactory.server.SimpleRequestProcessor;
import com.google.web.bindery.requestfactory.server.testing.InProcessRequestTransport;
import com.google.web.bindery.requestfactory.vm.*;

public class AppRequestFactoryProvider {
    
    //private static final TimeEntryWidgetRequestFactory rf = GWT.create(TimeEntryWidgetRequestFactory.class);

    public static TimesheetRequestFactory get() {
        ServiceLayer serviceLayer = ServiceLayer.create();
        SimpleRequestProcessor processor = new SimpleRequestProcessor(serviceLayer);
        EventBus eventBus = new SimpleEventBus();
        TimesheetRequestFactory requestFactory = RequestFactorySource.create(TimesheetRequestFactory.class);
        requestFactory.initialize(eventBus, new InProcessRequestTransport(processor));
        return requestFactory;
    }
}
