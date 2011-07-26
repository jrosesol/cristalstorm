package com.cristal.storm.prototype.shared.service;

import java.util.Collections;
import java.util.List;

import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.DATA_ENVENT_TYPE;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class CommandWatchDog implements HasHandlers {

    private final EventBus eventBus;

    private final TimesheetRequestFactory rf = GWT.create(TimesheetRequestFactory.class);

    @Inject
    public CommandWatchDog(final EventBus eventBus) {
        this.eventBus = eventBus;
        rf.initialize(this.eventBus);
    }

    public void deleteTimeEntry(TimeEntryProxy timeEntry) {
        // final String methodName = getClass().getEnclosingMethod().getName();

        // Delete and get again
        rf.timeEntryRequest().deleteTimeEntry(timeEntry).fire(getVoidReceiver("deleteTimeEntry"));
    }

    public void listAllTimeEntries(final List<TimeEntryProxy> dstResult) {
        rf.timeEntryRequest().listAll().fire(getListReceiver("listAll", dstResult, eventBus));
     }

    public static Receiver<Void> getVoidReceiver(final String serviceCallName) {
        Receiver<Void> voidReceiver = new Receiver<Void>() {

            @Override
            public void onSuccess(Void response) {
                System.out.print(serviceCallName);
            }

            @Override
            public void onFailure(ServerFailure error) {
                System.out.print(serviceCallName);
            }
        };

        return voidReceiver;
    }

    /**
     * @param <T>
     * @param serviceCallName
     * @param type
     * @return
     */
    public static <T> Receiver<List<T>> getListReceiver(final String serviceCallName, final List<T> dstResult,
            final EventBus eventBus) {
        Receiver<List<T>> voidReceiver = new Receiver<List<T>>() {

            @Override
            public void onSuccess(List<T> response) {
                System.out.print(serviceCallName);

                // Copy all elements of the collection
                dstResult.clear();
                for (T object : response) {
                    dstResult.add(object);
                }

                // Notify the handlers a data event is over successfully
                UpdateDataBindedObjectsEvent.fire(eventBus, DATA_ENVENT_TYPE.LIST_ALL_TIME_ENTRIES);
            }

            @Override
            public void onFailure(ServerFailure error) {
                System.out.print(serviceCallName);
            }
        };

        return voidReceiver;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

}
