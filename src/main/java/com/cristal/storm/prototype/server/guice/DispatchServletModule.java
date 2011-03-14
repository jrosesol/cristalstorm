package com.cristal.storm.prototype.server.guice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.ActionImpl;

/**
 * For more details see: <a href="http://code.google.com/p/google-guice/wiki/Servlets">Guice Servlet</a>
 * 
 * @author JROSE-HP
 *
 */
public class DispatchServletModule extends ServletModule {

    // Add spring context to initialize the Entity persistence framework
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext(
    "classpath*:META-INF/spring/applicationContext*.xml");

    @Override
    public void configureServlets() {
        serve("/" + ActionImpl.DEFAULT_SERVICE_NAME)
        .with(DispatchServiceImpl.class);
    }

}