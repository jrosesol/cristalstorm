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

package com.cristal.storm.prototype.client.mvp.presenter;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.mvp.view.MainPageUiHandlers;
import com.cristal.storm.prototype.client.util.Resources;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;

/**
 * @author Jose Rose
 */
public class MainPagePresenter
        extends
        Presenter<MainPagePresenter.MainPageViewInterface, MainPagePresenter.MainPageProxy>
        implements MainPageUiHandlers {

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "main";

    private final PlaceManager placeManager;

    private final DispatchAsync dispatcher;

    private final DataStoreProxy dataProxy;

    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();
    
    static {
        Resources.INSTANCE.style().ensureInjected();  
    }

    // /////////////////////////////////////////////////////////////////////////
    // Interfaces
    // /////////////////////////////////////////////////////////////////////////

    /**
     * {@link MainPagePresenter}'s proxy.
     */
    @ProxyStandard
    @NameToken(nameToken)
    public interface MainPageProxy extends Proxy<MainPagePresenter>, Place {
    }

    /**
     * {@link MainPagePresenter}'s view. Here it extends HasUiHandlers to be
     * able to call setUiHandlers.
     */
    public interface MainPageViewInterface extends View,
            HasUiHandlers<MainPageUiHandlers> {

    }

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    @Inject
    public MainPagePresenter(final EventBus eventBus,
            final MainPageViewInterface view, final MainPageProxy proxy,
            final PlaceManager placeManager, final DispatchAsync dispatcher,
            final DataStoreProxy dataProxy) {
        super(eventBus, view, proxy);
        getView().setUiHandlers(this);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.dataProxy = dataProxy;

        
    }

    @Inject
    CompanyPresenter companyPresenter;

    @Inject
    ReportsPresenter reports;

    @Inject
    TasksPresenter tasks;

    @Inject
    TimesheetPresenter timesheets;
    
    @Inject
    ActionBarPresenter actionBar;

    // @Inject
    // public void injectPresenters(final CompanyPresenter companyPresenter,
    // final ReportsPresenter reports, final TasksPresenter tasks,
    // final TimesheetPresenter timesheets) {
    //
    // }

    // /////////////////////////////////////////////////////////////////////////
    // Handlers
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    protected void onBind() {
        super.onBind();
    }

    @Override
    protected void revealInParent() {
        RevealRootLayoutContentEvent.fire(this, this);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    // /////////////////////////////////////////////////////////////////////////

}
