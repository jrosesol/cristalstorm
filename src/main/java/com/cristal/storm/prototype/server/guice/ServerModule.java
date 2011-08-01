/**
 *
 */

package com.cristal.storm.prototype.server.guice;

import com.cristal.storm.prototype.server.service.ObjectifyDao;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * Module which binds the handlers and configurations.
 * 
 * @author Jose Rose
 */
public class ServerModule extends HandlerModule {
   
    @Override
    protected void configureHandlers() {
        System.out.print("configureHandlers()");
        bind(ObjectifyDao.class).asEagerSingleton();
    }
}
