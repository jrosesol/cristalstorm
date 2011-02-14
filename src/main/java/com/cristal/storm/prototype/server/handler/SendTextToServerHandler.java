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

import org.apache.log4j.Logger;
import org.ihe.xds.consumer.RetrieveDocumentSetImpl;
import org.ihe.xds.consumer.exception.VerboseIllegalArgumentException;
import org.ihe.xds.consumer.exception.XDSException;

public class SendTextToServerHandler implements
        ActionHandler<SendTextToServer, SendTextToServerResult> {

    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;
    
    // Define a static logger variable so that it references the
    // Logger instance named "MyApp".
    static Logger logger = Logger.getLogger(RetrieveDocumentSetImpl.class);

    @Inject
    SendTextToServerHandler(ServletContext servletContext,
            Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
        
        //File conf = new File("./WEB-INF/log4jconfig.xml");
        //org.apache.log4j.xml.DOMConfigurator.configure(conf.getAbsolutePath());

        File axis2conf = new File("./WEB-INF/axis2.xml");
        System.setProperty("axis2.xml", axis2conf.getAbsolutePath());
    }

    public SendTextToServerResult execute(SendTextToServer fromClientAction,
            ExecutionContext context) throws ActionException {
                
        String docUID = fromClientAction.getDocUID();
        String repoUID = fromClientAction.getRepoUID();
        
        String axis2prop = System.getProperty("axis2.xml");

        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(docUID)) {
            // If the input is not valid, throw an IllegalArgumentException back
            // to
            // the client.
            throw new ActionException("Name must be at least 3 characters long");
        }
        
        String attachement = null;
        String attachmentType = null;
        String mimeType = null;
        RetrieveDocumentSetImpl myXDSConsumerRequestHandler = null;
        try {
            myXDSConsumerRequestHandler = new RetrieveDocumentSetImpl(fromClientAction.getRepoURI());
            myXDSConsumerRequestHandler.retrieveDocumentSet(repoUID, docUID);
            
            mimeType = myXDSConsumerRequestHandler.getDocumentMimeType(docUID, repoUID);
            attachement = myXDSConsumerRequestHandler.getSingleAttachement(docUID, repoUID).toString();
            attachmentType = "Attachments";
        } catch (VerboseIllegalArgumentException e) {
            myXDSConsumerRequestHandler = null;
            attachmentType = "Error!";
            attachement = e.toString();

            logger.debug("Exception triggered during Doc Retrieve : " + e);
        } catch (XDSException e) {
            myXDSConsumerRequestHandler = null;
            attachmentType = "Error!";
            attachement = e.toString();

            logger.debug("Exception triggered during Doc Retrieve : " + e);
        }

        String serverInfo = servletContext.getServerInfo();
        String userAgent = requestProvider.get().getHeader("User-Agent");
        
        String serverResponse = "XDS.b, " + docUID
        + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent
        + ".<br><br>The XDS.b Retrieve Document Set returned "
        + "<hr><p>" + attachmentType + ":"
        + "<br><p><table border=\"1\"><tr><td>" + mimeType + "</td><td width=\"100\" height=\"200\">" + attachement + "</td></tr></table>";

        return new SendTextToServerResult(serverResponse);
    }

    public Class<SendTextToServer> getActionType() {
        return SendTextToServer.class;
    }

    public void undo(SendTextToServer action, SendTextToServerResult result,
            ExecutionContext context) throws ActionException {
        // Not undoable
    }

}