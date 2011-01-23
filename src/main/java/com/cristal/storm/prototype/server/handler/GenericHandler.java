/**
 * 
 */
package com.cristal.storm.prototype.server.handler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.Result;

/**
 * @author JROSE-HP
 * 
 */
public abstract class GenericHandler<A extends Action<R>, R extends Result>
        implements ActionHandler<A, R> {

    public GenericHandler() {
        super();
    }

    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    public GenericHandler(ServletContext servletContext,
            Provider<HttpServletRequest> requestProvider) {
        this.requestProvider = requestProvider;
        this.servletContext = servletContext;
    }

}
