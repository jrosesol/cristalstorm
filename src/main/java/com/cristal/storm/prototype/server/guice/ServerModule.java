/**
 *
 */

package com.cristal.storm.prototype.server.guice;

import com.cristal.storm.prototype.server.service.ObjectifyDao;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * Module which binds the handlers and configurations.
 * 
 * @author Jose Rose
 */
public class ServerModule extends HandlerModule {
   
    @Override
    protected void configureHandlers() {
        bind(RequestFactoryServlet.class).in(Singleton.class);
    }
        
}
