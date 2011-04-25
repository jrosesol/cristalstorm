/**
 *
 */

package com.cristal.storm.prototype.server.guice;

import com.cristal.storm.prototype.server.action.SendTextToServerHandler;
import com.cristal.storm.prototype.shared.action.SendTextToServer;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * Module which binds the handlers and configurations.
 * 
 * @author Jose Rose
 */
public class ServerModule extends HandlerModule {
   
    @Override
    protected void configureHandlers() {
        bindHandler(SendTextToServer.class, SendTextToServerHandler.class);
    }
}
