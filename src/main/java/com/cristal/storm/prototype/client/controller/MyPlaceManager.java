package com.cristal.storm.prototype.client.controller;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.cristal.storm.prototype.client.mvp.presenter.AppStartPagePresenter;

public class MyPlaceManager extends PlaceManagerImpl {

    @Inject
    public MyPlaceManager(SimpleEventBus eventBus, TokenFormatter tokenFormatter) {
        super(eventBus, tokenFormatter);
    }

    @Override
    public void revealDefaultPlace() {
        revealPlace(new PlaceRequest(AppStartPagePresenter.nameToken));
    }

//    @Override
//    public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
//        int a = 3;
//        a = 4;
//    }
//    
//    @Override
//    public void revealErrorPlace(String invalidHistoryToken) {
//        int a = 3;
//        a = 4;
//    }
}
