/**
 * 
 */
package com.cristal.storm.prototype.server.action;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.server.domain.MCE;
import com.cristal.storm.prototype.server.domain.Tag;
import com.cristal.storm.prototype.shared.FieldVerifier;
import com.cristal.storm.prototype.shared.action.SendMceToServer;
import com.cristal.storm.prototype.shared.action.SendMceToServerResult;
import com.cristal.storm.prototype.shared.action.SendTextToServer;
import com.cristal.storm.prototype.shared.action.SendTextToServerResult;
import com.cristal.storm.prototype.shared.domain.MceDto;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author Admin
 * 
 */
public class SendMceToServerHandler implements
        ActionHandler<SendMceToServer, SendMceToServerResult> {

    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;    

    // Define a static logger variable so that it references the Logger
    static Logger logger = Logger.getLogger(DataStoreProxy.class);
    
    @Inject
    SendMceToServerHandler(ServletContext servletContext,
            Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    @Override
    public SendMceToServerResult execute(SendMceToServer action,
            ExecutionContext context) throws ActionException {
        
        Date timeStamp = new Date();
        MCE aNewMce = new MCE();
        
        Tag newTag = new Tag();
        newTag.setTag("tag");
        
        Set<Tag> newTagSet = new HashSet<Tag>();
        newTagSet.add(newTag);
        
        try {
            aNewMce.setId((long) 1);
            aNewMce.setTag(newTagSet);
            aNewMce.setUri("uri");
            aNewMce.setCreated(timeStamp);
            aNewMce.persist();
            logger.info("MCE Persisted!!!");
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        
    	//TODO: please handle real stuff
        return new SendMceToServerResult(new MceDto());
    }

    @Override
    public Class<SendMceToServer> getActionType() {
        return SendMceToServer.class;
    }

    @Override
    public void undo(SendMceToServer action, SendMceToServerResult result,
            ExecutionContext context) throws ActionException {
        // Not undoable
    }
}