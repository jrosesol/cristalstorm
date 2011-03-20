/**
 *
 */

package com.cristal.storm.prototype.server.guice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cristal.storm.prototype.server.action.GetMceListHandler;
import com.cristal.storm.prototype.server.action.SendMceToServerHandler;
import com.cristal.storm.prototype.server.action.SendTextToServerHandler;
import com.cristal.storm.prototype.shared.action.GetMceListAction;
import com.cristal.storm.prototype.shared.action.SendMceToServer;
import com.cristal.storm.prototype.shared.action.SendTextToServer;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * Module which binds the handlers and configurations.
 * 
 * @author José Rose
 */
public class ServerModule extends HandlerModule {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext*.xml");
    
    @Override
    protected void configureHandlers() {
        bindHandler(SendTextToServer.class, SendTextToServerHandler.class);
        bindHandler(SendMceToServer.class, SendMceToServerHandler.class);
        bindHandler(GetMceListAction.class, GetMceListHandler.class);
    }
}
