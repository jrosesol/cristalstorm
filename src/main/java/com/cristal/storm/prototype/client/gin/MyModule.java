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

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.controller.MyPlaceManager;
import com.cristal.storm.prototype.client.mvp.presenter.ActionBarPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.CompanyPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.MainPagePresenter;
import com.cristal.storm.prototype.client.mvp.presenter.MyRootPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.ReportsPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TasksPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TimesheetPresenter;
import com.cristal.storm.prototype.client.mvp.view.ActionBarView;
import com.cristal.storm.prototype.client.mvp.view.CompanyView;
import com.cristal.storm.prototype.client.mvp.view.MainPageView;
import com.cristal.storm.prototype.client.mvp.view.ReportsView;
import com.cristal.storm.prototype.client.mvp.view.TasksView;
import com.cristal.storm.prototype.client.mvp.view.TimesheetView;
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
 * @author Jose Rose
 */
public class MyModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
        bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(
                Singleton.class);
        //bind(RootPresenter.class).asEagerSingleton();
        bind(RootPresenter.class).to(MyRootPresenter.class).asEagerSingleton();
        bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class)
                .in(Singleton.class);

        // User bindings
        bind(DataStoreProxy.class).asEagerSingleton();

        // Presenters
        bindPresenter(MainPagePresenter.class,
                MainPagePresenter.MainPageViewInterface.class,
                MainPageView.class, MainPagePresenter.MainPageProxy.class);
        bindPresenter(TimesheetPresenter.class,
                TimesheetPresenter.TimesheetViewInterface.class,
                TimesheetView.class, TimesheetPresenter.TimesheetProxy.class);
        bindPresenter(ReportsPresenter.class,
                ReportsPresenter.ReportsViewInterface.class, ReportsView.class,
                ReportsPresenter.ReportsProxy.class);
        bindPresenter(TasksPresenter.class,
                TasksPresenter.TasksViewInterface.class, TasksView.class,
                TasksPresenter.TasksProxy.class);
        bindPresenter(CompanyPresenter.class,
                CompanyPresenter.CompanyViewInterface.class, CompanyView.class,
                CompanyPresenter.CompanyProxy.class);
        bindPresenter(ActionBarPresenter.class,
                ActionBarPresenter.ActionBarViewInterface.class, ActionBarView.class,
                ActionBarPresenter.ActionBarProxy.class);
    }
}