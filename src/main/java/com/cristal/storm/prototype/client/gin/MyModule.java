/**
 * Copyright 2010 ArcBees Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cristal.storm.prototype.client.gin;

import com.cristal.storm.prototype.client.ResponsePresenter;
import com.cristal.storm.prototype.client.ResponseView;
import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.controller.MyPlaceManager;
import com.cristal.storm.prototype.client.mvp.presenter.MainPagePresenter;
import com.cristal.storm.prototype.client.mvp.view.MainPageView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;

import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

/**
 * @author Jos� Rose
 */
public class MyModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
    bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
    bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(
        Singleton.class);
    bind(RootPresenter.class).asEagerSingleton();
    bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(
        Singleton.class);
    
    // User bindings
    bind(DataStoreProxy.class).asEagerSingleton();

    // Presenters
    bindPresenter(MainPagePresenter.class, MainPagePresenter.MainPageViewInterface.class,
        MainPageView.class, MainPagePresenter.MainPageProxy.class);

    bindPresenter(ResponsePresenter.class, ResponsePresenter.MyView.class,
        ResponseView.class, ResponsePresenter.MyProxy.class);
  }
}