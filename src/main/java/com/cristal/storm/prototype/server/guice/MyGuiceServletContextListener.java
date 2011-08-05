package com.cristal.storm.prototype.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.cristal.storm.prototype.server.guice.DispatchServletModule;

public class MyGuiceServletContextListener extends GuiceServletContextListener {


  @Override
  protected Injector getInjector() {
    return Guice
        .createInjector(new ServerModule(), new DispatchServletModule(), new DAOModule());
  }
}