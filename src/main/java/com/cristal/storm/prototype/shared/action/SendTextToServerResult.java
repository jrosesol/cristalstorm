/**
 * 
 */

package com.cristal.storm.prototype.shared.action;

import com.gwtplatform.dispatch.shared.Result;

/**
 * The result of a {@link SendTextToServer} action.
 */
public class SendTextToServerResult implements Result {

    ///////////////////////////////////////////////////////////////////////////
    // Data
    ///////////////////////////////////////////////////////////////////////////
    private static final long serialVersionUID = 4621412923270714515L;

    private String response;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public SendTextToServerResult(final String response) {
        this.response = response;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendTextToServerResult() {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    public String getResponse() {
        return response;
    }

}
