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
import com.cristal.storm.prototype.client.mvp.presenter.CompanyPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.MainPagePresenter;
import com.cristal.storm.prototype.client.mvp.presenter.MyRootPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.ProjectPopupDetailsPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.ProjectPopupDetailsPresenter.ProjectPopupDetailsViewInterface;
import com.cristal.storm.prototype.client.mvp.presenter.ReportsPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TasksPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TimesheetCellListPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TimesheetPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TimesheetCellListPresenter.TimesheetCellListViewInterface;
import com.cristal.storm.prototype.client.mvp.view.CompanyView;
import com.cristal.storm.prototype.client.mvp.view.MainPageView;
import com.cristal.storm.prototype.client.mvp.view.ProjectPopupDetailsView;
import com.cristal.storm.prototype.client.mvp.view.ReportsView;
import com.cristal.storm.prototype.client.mvp.view.TasksView;
import com.cristal.storm.prototype.client.mvp.view.TimesheetCellListView;
import com.cristal.storm.prototype.client.mvp.view.TimesheetView;
import com.cristal.storm.prototype.client.ui.ActivityCalendarWidgetPresenter;
import com.cristal.storm.prototype.client.ui.ActivityCalendarWidgetPresenter.ActivityCalendarWidgetViewInterface;
import com.cristal.storm.prototype.client.ui.ActivityCalendarWidgetView;
import com.cristal.storm.prototype.client.util.DemoDataLoader;
import com.cristal.storm.prototype.shared.service.CommandWatchDog;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.AccountRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.ActivityListRequestContext;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;


/**
 * @author Jose Rose
 */
public class MyModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
        bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
        // bind(RootPresenter.class).asEagerSingleton();
        bind(RootPresenter.class).to(MyRootPresenter.class).asEagerSingleton();

        // User bindings
        bind(DataStoreProxy.class).asEagerSingleton();
        bind(CommandWatchDog.class).asEagerSingleton();
        //bind(TimesheetRequestFactory.class).asEagerSingleton();
        bind(DemoDataLoader.class).asEagerSingleton();

        // Presenters
        bindPresenter(MainPagePresenter.class, MainPagePresenter.MainPageViewInterface.class, MainPageView.class,
                      MainPagePresenter.MainPageProxy.class);
        bindPresenter(TimesheetPresenter.class, TimesheetPresenter.TimesheetViewInterface.class, TimesheetView.class,
                      TimesheetPresenter.TimesheetProxy.class);
        bindPresenter(ReportsPresenter.class, ReportsPresenter.ReportsViewInterface.class, ReportsView.class,
                      ReportsPresenter.ReportsProxy.class);
        bindPresenter(TasksPresenter.class, TasksPresenter.TasksViewInterface.class, TasksView.class,
                      TasksPresenter.TasksProxy.class);
        bindPresenter(CompanyPresenter.class, CompanyPresenter.CompanyViewInterface.class, CompanyView.class,
                      CompanyPresenter.CompanyProxy.class);

        // Presenter widgets
        bindSingletonPresenterWidget(TimesheetCellListPresenter.class, TimesheetCellListViewInterface.class,
                                     TimesheetCellListView.class);

        bindSingletonPresenterWidget(ProjectPopupDetailsPresenter.class,
                                     ProjectPopupDetailsViewInterface.class, ProjectPopupDetailsView.class);

        bindPresenterWidget(ActivityCalendarWidgetPresenter.class,
                            ActivityCalendarWidgetViewInterface.class, ActivityCalendarWidgetView.class);
    }

    @Provides
    @Singleton
    public TimesheetRequestFactory createTimesheetRequestFactory( EventBus eventBus ) {
        TimesheetRequestFactory factory = GWT.create( TimesheetRequestFactory.class );
        factory.initialize( eventBus );
        return factory;
    }

    @Provides
    public TimeEntryRequestContext createTimeEntryRequestContext( TimesheetRequestFactory factory ) {
        return factory.timeEntryRequest();
    }

    @Provides
    public ActivityListRequestContext createActivityListRequestContext( TimesheetRequestFactory factory ) {
        return factory.activityListRequest();
    }

    @Provides
    public AccountRequestContext createAccountRequestContext( TimesheetRequestFactory factory ) {
        return factory.accountRequest();
    }
}