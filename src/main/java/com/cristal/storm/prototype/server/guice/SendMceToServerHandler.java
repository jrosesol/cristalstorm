/**
 * 
 */
package com.cristal.storm.prototype.server.guice;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cristal.storm.prototype.server.domain.MCE;
import com.cristal.storm.prototype.shared.FieldVerifier;
import com.cristal.storm.prototype.shared.SendMceToServer;
import com.cristal.storm.prototype.shared.SendMceToServerResult;
import com.cristal.storm.prototype.shared.SendTextToServer;
import com.cristal.storm.prototype.shared.SendTextToServerResult;
import com.cristal.storm.prototype.shared.domain.MCEDto;
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
    
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext*.xml");

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
        aNewMce.setId((long) 1);
        aNewMce.setTag("tag");
        aNewMce.setUri("uri");
        aNewMce.setCreated(timeStamp);
        
        try {
            aNewMce.persist();
            System.out.print("MCE Persisted!!!");
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        
        
        return new SendMceToServerResult(new MCEDto());
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
