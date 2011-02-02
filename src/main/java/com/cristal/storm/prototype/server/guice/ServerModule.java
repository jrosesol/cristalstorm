package com.cristal.storm.prototype.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.cristal.storm.prototype.server.handler.SendTextToServerHandler;
import com.cristal.storm.prototype.shared.action.SendTextToServer;

public class ServerModule extends HandlerModule {

    @Override
    protected void configureHandlers() {
        bindHandler(SendTextToServer.class, SendTextToServerHandler.class);
    }
}