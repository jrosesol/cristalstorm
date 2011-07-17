/**
 * 
 */
package com.cristal.storm.prototype.client.controller;

import java.util.List;

import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.shared.action.SendTextToServer;
import com.cristal.storm.prototype.shared.action.SendTextToServerResult;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;


/**
 * @author Admin
 * 
 */
public class DataStoreProxy implements HasHandlers {
    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    private final DispatchAsync dispatcher;
    
    private final EventBus eventBus;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    /**
     * 
     */
    @Inject
    public DataStoreProxy(final DispatchAsync dispatcher, final EventBus eventBus) {
        this.dispatcher = dispatcher;
        this.eventBus = eventBus;
    }
    
    // /////////////////////////////////////////////////////////////////////////
    // Handlers
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    public void storeMce(String text) {
        dispatcher.execute(new SendTextToServer(""),
                new AsyncCallback<SendTextToServerResult>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        System.out.print("MCE stored failed\n");
                        System.out.print("Cause:\n");
                        caught.printStackTrace();
                    }

                    @Override
                    public void onSuccess(SendTextToServerResult result) {

                        UpdateDataBindedObjectsEvent.fire(eventBus);
                    }
                });
    }



    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}
