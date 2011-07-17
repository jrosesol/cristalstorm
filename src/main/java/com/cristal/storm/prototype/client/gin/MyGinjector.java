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

import com.cristal.storm.prototype.client.mvp.presenter.ActionBarPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.CompanyPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.MainPagePresenter;
import com.cristal.storm.prototype.client.mvp.presenter.ReportsPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TasksPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TimesheetPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;

import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import com.cristal.storm.prototype.client.gin.MyModule;

/**
 * TODO: Add comments for MyGinjector
 * 
 */
@GinModules({ DispatchAsyncModule.class, MyModule.class })
public interface MyGinjector extends Ginjector {
	EventBus getEventBus();

	Provider<MainPagePresenter> getMainPagePresenter();

	PlaceManager getPlaceManager();
	
	// Asynch providers
	AsyncProvider<TimesheetPresenter> getTimesheetPresenter();
	AsyncProvider<TasksPresenter> getTasksPresenter();
	AsyncProvider<ReportsPresenter> getReportsPresenter();
	AsyncProvider<CompanyPresenter> getCompanyPresenter();
	AsyncProvider<ActionBarPresenter> getActionBar();
}