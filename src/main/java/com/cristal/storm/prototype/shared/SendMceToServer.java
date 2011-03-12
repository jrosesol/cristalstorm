/**
 * 
 */
package com.cristal.storm.prototype.shared;

import com.cristal.storm.prototype.shared.domain.MCEDto;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * @author Admin
 * 
 */
public class SendMceToServer extends
        UnsecuredActionImpl<SendMceToServerResult> {

    private static final long serialVersionUID = 4621412923270714515L;

    private MCEDto mce;

    public SendMceToServer(final MCEDto mce) {
        this.mce = mce;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendMceToServer() {
    }

    public MCEDto getMceToServer() {
        return mce;
    }
}
