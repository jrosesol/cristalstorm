package com.cristal.storm.prototype.shared.action;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.dispatch.shared.Result;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class GenericAction extends
        UnsecuredActionImpl<GenericAction.GenericResult> {

    public class GenericResult implements Result {

    }

    public void defaultExecution(DispatchAsync dispatcher) throws Exception {
        dispatcher.execute(this, new ExecutionCallback());
    }

    private class ExecutionCallback implements
            AsyncCallback<GenericAction.GenericResult> {

        public void onFailure(Throwable caught) {
            try {
                throw caught;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        public void onSuccess(GenericAction.GenericResult result) {

        }
    }

}
