/**
 * 
 */

package com.cristal.storm.prototype.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class SendTextToServer extends
        UnsecuredActionImpl<SendTextToServerResult> {

    ///////////////////////////////////////////////////////////////////////////
    // Data
    ///////////////////////////////////////////////////////////////////////////
    private static final long serialVersionUID = 4621412923270714515L;

    private String textToServer;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public SendTextToServer(final String textToServer) {
        this.textToServer = textToServer;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendTextToServer() {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    public String getTextToServer() {
        return textToServer;
    }
}
