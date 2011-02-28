package com.cristal.storm.prototype.client.gin;

import com.google.inject.Singleton;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.cristal.storm.prototype.client.controller.MyPlaceManager;
import com.cristal.storm.prototype.client.mvp.presenter.AppStartPagePresenter;
import com.cristal.storm.prototype.client.mvp.view.AppStartPageView;


public class MyModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
        bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(
                Singleton.class);
        bind(RootPresenter.class).asEagerSingleton();
        bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class)
                .in(Singleton.class);

        // Presenters
        bindPresenter(AppStartPagePresenter.class,
                AppStartPagePresenter.AppStartPageViewInterface.class,
                AppStartPageView.class,
                AppStartPagePresenter.AppStartPageProxy.class);
        
//        bindPresenter(CommandLineBoxPresenter.class,
//                CommandLineBoxPresenter.CommandLineBoxViewInterface.class, CommandLineBoxView.class,
//                CommandLineBoxPresenter.CommandLineBoxProxy.class);
//        
//        bindSingletonPresenterWidget(CommandHandlerPresenter.class,
//                CommandHandlerPresenter.CommandHandlerViewInterface.class, CommandHandlerView.class);
//
//        bindSingletonPresenterWidget(PatientRequiredInfoPresenterWidget.class,
//                PatientRequiredInfoPresenterWidget.PatientRequiredInfoViewInterface.class, PatientRequiredInfoView.class);
//        
//        bindSingletonPresenterWidget(
//                PatientSinglePrescriptionPresenterWidget.class,
//                PatientSinglePrescriptionPresenterWidget.PatientSinglePrescriptionViewInterface.class,
//                PatientSinglePrescriptionView.class);
        
        // bindPresenter(ResponsePresenter.class,
        // ResponsePresenter.MyView.class,
        // ResponseView.class, ResponsePresenter.MyProxy.class);
    }

}
