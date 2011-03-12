/**
 * 
 */
package com.cristal.storm.prototype.shared;

import com.cristal.storm.prototype.shared.domain.MCEDto;
import com.gwtplatform.dispatch.shared.Result;

/**
 * @author Admin
 *
 */
public class SendMceToServerResult implements Result {

    private static final long serialVersionUID = 4621412923270714515L;

    private MCEDto mce;

    public SendMceToServerResult(final MCEDto mce) {
      this.mce = mce;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendMceToServerResult() {
    }

    public MCEDto getResponse() {
      return mce;
    }

}
