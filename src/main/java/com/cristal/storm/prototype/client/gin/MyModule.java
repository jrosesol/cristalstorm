package com.cristal.storm.prototype.client.gin;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.DefaultEventBus;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.cristal.storm.prototype.client.controller.MyPlaceManager;
import com.cristal.storm.prototype.client.mvp.presenter.AppStartPagePresenter;
import com.cristal.storm.prototype.client.mvp.view.AppStartPageView;
import com.cristal.storm.prototype.client.ui.CommandDigester;
import com.cristal.storm.prototype.client.ui.CommandHandlerPresenter;
import com.cristal.storm.prototype.client.ui.CommandHandlerView;
import com.cristal.storm.prototype.client.ui.CommandLineBoxPresenter;
import com.cristal.storm.prototype.client.ui.CommandLineBoxView;
import com.cristal.storm.prototype.client.ui.PatientRequiredInfoPresenterWidget;
import com.cristal.storm.prototype.client.ui.PatientRequiredInfoView;
import com.cristal.storm.prototype.client.ui.PatientSinglePrescriptionPresenterWidget;
import com.cristal.storm.prototype.client.ui.PatientSinglePrescriptionView;

public class MyModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
        bind(CommandDigester.class).asEagerSingleton();//in(Singleton.class);
        //bind(CommandHistory.class).to(CommandDigester.class);
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
        
        bindPresenter(CommandLineBoxPresenter.class,
                CommandLineBoxPresenter.CommandLineBoxViewInterface.class, CommandLineBoxView.class,
                CommandLineBoxPresenter.CommandLineBoxProxy.class);
        
        bindSingletonPresenterWidget(CommandHandlerPresenter.class,
                CommandHandlerPresenter.CommandHandlerViewInterface.class, CommandHandlerView.class);

        bindSingletonPresenterWidget(PatientRequiredInfoPresenterWidget.class,
                PatientRequiredInfoPresenterWidget.PatientRequiredInfoViewInterface.class, PatientRequiredInfoView.class);
        
        bindSingletonPresenterWidget(
                PatientSinglePrescriptionPresenterWidget.class,
                PatientSinglePrescriptionPresenterWidget.PatientSinglePrescriptionViewInterface.class,
                PatientSinglePrescriptionView.class);
        
        // bindPresenter(ResponsePresenter.class,
        // ResponsePresenter.MyView.class,
        // ResponseView.class, ResponsePresenter.MyProxy.class);
    }

}
