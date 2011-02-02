package com.cristal.storm.prototype.shared.action;

import com.gwtplatform.dispatch.shared.Result;

public class SendTextToServerResult implements Result {

    private static final long serialVersionUID = 4621412923270714515L;

    private String response;

    public SendTextToServerResult(final String response) {
        this.response = response;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendTextToServerResult() {
    }

    public String getResponse() {
        return response;
    }
}
