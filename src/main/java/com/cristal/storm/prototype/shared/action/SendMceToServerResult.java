/**
 * 
 */
package com.cristal.storm.prototype.shared.action;

import com.cristal.storm.prototype.shared.domain.MceDto;
import com.gwtplatform.dispatch.shared.Result;

/**
 * @author Admin
 *
 */
public class SendMceToServerResult implements Result {

    private static final long serialVersionUID = 4621412923270714515L;

    private MceDto mce;

    public SendMceToServerResult(final MceDto mce) {
      this.mce = mce;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendMceToServerResult() {
    }

    public MceDto getResponse() {
      return mce;
    }

}
