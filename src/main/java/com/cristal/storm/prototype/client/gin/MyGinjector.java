package com.cristal.storm.prototype.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.cristal.storm.prototype.client.mvp.presenter.AppStartPagePresenter;


/**
 * TODO: Add comments for MyGinjector
 *
 */
@GinModules({ DispatchAsyncModule.class, MyModule.class })
public interface MyGinjector extends Ginjector {

    Provider<AppStartPagePresenter> getAppStartPagePresenter();

    EventBus getEventBus();

    PlaceManager getPlaceManager();

    ProxyFailureHandler getProxyFailureHandler();
}
