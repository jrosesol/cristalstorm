package com.cristal.storm.prototype.server.handler;

import com.google.inject.Inject;
import com.google.inject.Provider;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.cristal.storm.prototype.shared.FieldVerifier;
import com.cristal.storm.prototype.shared.action.SendTextToServer;
import com.cristal.storm.prototype.shared.action.SendTextToServerResult;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class SendTextToServerHandler implements
    ActionHandler<SendTextToServer, SendTextToServerResult> {

  private Provider<HttpServletRequest> requestProvider;
  private ServletContext servletContext;

  @Inject
  SendTextToServerHandler(ServletContext servletContext,
      Provider<HttpServletRequest> requestProvider) {
    this.servletContext = servletContext;
    this.requestProvider = requestProvider;
  }

  public SendTextToServerResult execute(SendTextToServer action,
      ExecutionContext context) throws ActionException {

    String input = action.getTextToServer();

    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back
      // to
      // the client.
      throw new ActionException("Name must be at least 3 characters long");
    }

    String serverInfo = servletContext.getServerInfo();
    String userAgent = requestProvider.get().getHeader("User-Agent");
    return new SendTextToServerResult("Hello, " + input
        + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent);
  }

  public Class<SendTextToServer> getActionType() {
    return SendTextToServer.class;
  }

  public void undo(SendTextToServer action, SendTextToServerResult result,
      ExecutionContext context) throws ActionException {
    // Not undoable
  }

}