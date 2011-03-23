/**
 * 
 */
package com.cristal.storm.prototype.client.controller;

import java.util.List;

import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.shared.action.GetMceListAction;
import com.cristal.storm.prototype.shared.action.GetMceListResult;
import com.cristal.storm.prototype.shared.action.SendMceToServer;
import com.cristal.storm.prototype.shared.action.SendMceToServerResult;
import com.cristal.storm.prototype.shared.domain.MceDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;

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
    
    private List<MceDto> currentResults; 

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

    public void storeMce(MceDto mce) {
        dispatcher.execute(new SendMceToServer(mce),
                new AsyncCallback<SendMceToServerResult>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        System.out.print("MCE stored failed\n");
                        System.out.print("Cause:\n");
                        caught.printStackTrace();
                    }

                    @Override
                    public void onSuccess(SendMceToServerResult result) {
                        System.out.print("MCE store succeeded\n");

                        UpdateDataBindedObjectsEvent.fire(eventBus, result.getResponse());
                    }
                });
    }

    public void getMceList(int firstResult, int maxResults) {
        dispatcher.execute(new GetMceListAction(firstResult, maxResults),
                new AsyncCallback<GetMceListResult>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        System.out.print("MCE get list failed\n");
                        System.out.print("Cause: " + caught.getMessage() + "\n");
                    }

                    @Override
                    public void onSuccess(GetMceListResult result) {
                        System.out.print("MCE get list succeeded");
                        if (result.getMceDtoList().size() > 0) {
                            System.out.print("# or results: " + result.getMceDtoList().size());
                            
                            // set the current results
                            currentResults = result.getMceDtoList();
                            
                        }
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
    
    public List<MceDto> getCurrentMceList() {
        return currentResults;
    }
}
