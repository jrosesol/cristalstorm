/**
 *
 *
 * @author Jose Rose
 * 2011-04-29
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.cristal.storm.prototype.client.mvp.view.ActionBarUiHandlers;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

import com.google.gwt.event.shared.EventBus;

/**
 * ActionBar Presenter implementation
 * Handles the actions possible on the site
 */
public class ActionBarPresenter extends
        Presenter<ActionBarPresenter.ActionBarViewInterface, ActionBarPresenter.ActionBarProxy>
        implements ActionBarUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "a";

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link ActionBarPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface ActionBarProxy extends Proxy<ActionBarPresenter>, Place {
    }

    /**
     * {@link ActionBarPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface ActionBarViewInterface extends View,
            HasUiHandlers<ActionBarUiHandlers> {
    }

    /*
     * This interface is the link between the view and the presenter.
     * The presenter has to implement this interface.
     * This interface should be declared separetly.
    public interface ActionBarUiHandlers extends UiHandlers {
    }
     */

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public ActionBarPresenter(EventBus eventBus, ActionBarViewInterface view,
            ActionBarProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
        getView().setUiHandlers(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void revealInParent() {
        //RevealRootContentEvent.fire(this, this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}
