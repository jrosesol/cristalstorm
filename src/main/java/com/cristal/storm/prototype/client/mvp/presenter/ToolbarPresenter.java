/**
 *
 *
 * @author Jose Rose
 * Dec 21, 2010
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * Toolbar Presenter implementation
 * This is a toolbar presenter
 */
public class ToolbarPresenter extends
        Presenter<ToolbarPresenter.ToolbarViewInterface, ToolbarPresenter.ToolbarProxy> {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "toolbar";

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link ToolbarPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface ToolbarProxy extends Proxy<ToolbarPresenter>, Place {
    }

    /**
     * {@link ToolbarPresenter}'s view.
     */
    public interface ToolbarViewInterface extends View {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public ToolbarPresenter(EventBus eventBus, ToolbarViewInterface view, ToolbarProxy proxy,
            PlaceManager placeManager, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}