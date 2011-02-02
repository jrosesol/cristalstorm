package com.cristal.storm.prototype.server.handler;

import java.io.File;

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

import org.ihe.xds.consumer.RetrieveDocumentSetImpl;

public class SendTextToServerHandler implements
        ActionHandler<SendTextToServer, SendTextToServerResult> {

    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    SendTextToServerHandler(ServletContext servletContext,
            Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
        
        File conf = new File("./resources/conf/log4jconfig.xml");
        org.apache.log4j.xml.DOMConfigurator.configure(conf.getAbsolutePath());
    }

    public SendTextToServerResult execute(SendTextToServer fromClientAction,
            ExecutionContext context) throws ActionException {
        
        File conf = new File("./resources/conf/log4jconfig.xml");
        org.apache.log4j.xml.DOMConfigurator.configure(conf.getAbsolutePath());

        String docUID = fromClientAction.getDocUID();
        String repoUID = fromClientAction.getRepoUID();

        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(docUID)) {
            // If the input is not valid, throw an IllegalArgumentException back
            // to
            // the client.
            throw new ActionException("Name must be at least 3 characters long");
        }
        
        RetrieveDocumentSetImpl myXDSConsumerRequestHandler = null;
        try {
            myXDSConsumerRequestHandler = new RetrieveDocumentSetImpl(fromClientAction.getRepoURI());
            myXDSConsumerRequestHandler.retrieveDocumentSet(repoUID, docUID);

        } catch (Exception e) {
            //logger.error("Something went wrong while retrieving the documents set: " + e);
            int test = 0;
        }
        
        String attachement = myXDSConsumerRequestHandler.getAttachments().toString();

        String serverInfo = servletContext.getServerInfo();
        String userAgent = requestProvider.get().getHeader("User-Agent");
        return new SendTextToServerResult("Hello, " + docUID
                + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using:<br>" + userAgent
                + ".<br><br>The XDS.b Retrieve Document Set returned attachements:"
                + "<br>" + attachement);
    }

    public Class<SendTextToServer> getActionType() {
        return SendTextToServer.class;
    }

    public void undo(SendTextToServer action, SendTextToServerResult result,
            ExecutionContext context) throws ActionException {
        // Not undoable
    }

}