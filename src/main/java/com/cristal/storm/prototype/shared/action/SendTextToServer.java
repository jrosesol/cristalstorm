package com.cristal.storm.prototype.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SendTextToServer extends
    UnsecuredActionImpl<SendTextToServerResult> {

  private static final long serialVersionUID = 4621412923270714515L;

  private String textToServer;

  public SendTextToServer(final String textToServer) {
    this.textToServer = textToServer;
  }

  /**
   * For serialization only.
   */
  @SuppressWarnings("unused")
  private SendTextToServer() {
  }

  public String getTextToServer() {
    return textToServer;
  }
}
