package com.cristal.storm.prototype.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SendTextToServer extends
        UnsecuredActionImpl<SendTextToServerResult> {

    private static final long serialVersionUID = 4621412923270714515L;

    private String docUID;
    private String repoUID;
    private String repoURI;

    public SendTextToServer(final String docUID, final String repoUID,
            final String repoURI) {
        this.docUID = docUID;
        this.repoUID = repoUID;
        this.repoURI = repoURI;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendTextToServer() {
    }

    public String getDocUID() {
        return docUID;
    }

    public String getRepoUID() {
        return repoUID;
    }

    public String getRepoURI() {
        return repoURI;
    }
}
