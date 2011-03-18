/**
 *
 *
 * @author Jose Rose
 * 2011-03-14
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.cristal.storm.prototype.client.mvp.view.MceCollectionWidgetUiHandlers;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

import com.google.gwt.event.shared.EventBus;

/**
 * MceCollectionWidget Presenter implementation
 * Handles the display of the "Memory Cueu Elements".
 */
public class MceCollectionWidgetPresenter extends
    PresenterWidget<MceCollectionWidgetPresenter.MceCollectionWidgetViewInterface>
        implements MceCollectionWidgetUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////

    
    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link MceCollectionWidgetPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface MceCollectionWidgetViewInterface extends View,
            HasUiHandlers<MceCollectionWidgetUiHandlers> {
        public void addMceToCollection(String uriText, String tagsText);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public MceCollectionWidgetPresenter(EventBus eventBus, MceCollectionWidgetViewInterface view,
            PlaceManager placeManager, DispatchAsync dispatcher) {
        super(eventBus, view);
        getView().setUiHandlers(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}
