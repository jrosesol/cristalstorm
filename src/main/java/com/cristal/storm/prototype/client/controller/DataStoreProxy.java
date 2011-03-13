/**
 * 
 */
package com.cristal.storm.prototype.client.controller;

import java.util.List;

import com.cristal.storm.prototype.shared.action.GetMceListAction;
import com.cristal.storm.prototype.shared.action.GetMceListResult;
import com.cristal.storm.prototype.shared.action.SendMceToServer;
import com.cristal.storm.prototype.shared.action.SendMceToServerResult;
import com.cristal.storm.prototype.shared.domain.MceDto;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;

/**
 * @author Admin
 * 
 */
public class DataStoreProxy {
    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    final DispatchAsync dispatcher;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    /**
     * 
     */
    @Inject
    public DataStoreProxy(final DispatchAsync dispatcher) {
        this.dispatcher = dispatcher;
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
                        System.out.print("MCE stored failed");
                    }

                    @Override
                    public void onSuccess(SendMceToServerResult result) {
                        System.out.print("MCE store succeeded");
                    }
                });
    }
    
    public void getMceList(int firstResult, int maxResults) {
        dispatcher.execute(new GetMceListAction(firstResult, maxResults),
                new AsyncCallback<GetMceListResult>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        System.out.print("MCE get list failed");
                    }

                    @Override
                    public void onSuccess(GetMceListResult result) {
                        System.out.print("MCE get list succeeded");
                        if (result.getMceDtoList().size() > 0) {
                            System.out.print("# or results: " + result.getMceDtoList().size());
                        }
                    }
                });   
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////
}
