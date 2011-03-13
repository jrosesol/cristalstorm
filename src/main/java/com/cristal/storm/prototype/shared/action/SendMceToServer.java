/**
 * 
 */
package com.cristal.storm.prototype.shared.action;

import com.cristal.storm.prototype.shared.domain.MceDto;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * @author Admin
 * 
 */
public class SendMceToServer extends
        UnsecuredActionImpl<SendMceToServerResult> {

    private static final long serialVersionUID = 4621412923270714515L;

    private MceDto mce;

    public SendMceToServer(final MceDto mce) {
        this.mce = mce;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendMceToServer() {
    }

    public MceDto getMceToServer() {
        return mce;
    }
}
