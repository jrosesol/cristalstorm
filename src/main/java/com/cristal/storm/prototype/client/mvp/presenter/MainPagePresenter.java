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

import com.cristal.storm.prototype.client.mvp.presenter.AppStartPagePresenter.AppStartPageViewInterface;
import com.cristal.storm.prototype.client.mvp.view.AppStartPageUiHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * @author Philippe Beaudoin
 */


public class MainPagePresenter extends
    Presenter<MainPagePresenter.MainView, MainPagePresenter.MyProxy> implements
    AppStartPageUiHandlers {
  /**
   * {@link MainPagePresenter}'s proxy.
   */
  @ProxyStandard
  @NameToken(nameToken)
  public interface MyProxy extends Proxy<MainPagePresenter>, Place {
  }

  /**
   * {@link MainPagePresenter}'s view.
   */
  public interface MainView extends AppStartPageViewInterface {
    String getUriText();

    public String getTagsText();

    void addToMCECollection(String uriText, String tagsText);

    void tagCollectionFilter(String filter);
  }

  @Override
  protected void onBind() {
    super.onBind();
  }

  
  public static final String nameToken = "main";

  private final PlaceManager placeManager;

  @Inject
  public MainPagePresenter(EventBus eventBus, MainView view, MyProxy proxy,
      PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }
  
  @Override
  protected void revealInParent() {
    RevealRootContentEvent.fire(this, this);
  }
  
  @Override
  public void onStormit() {
      getView().addToMCECollection(getView().getUriText(),getView().getTagsText());
  }

}
