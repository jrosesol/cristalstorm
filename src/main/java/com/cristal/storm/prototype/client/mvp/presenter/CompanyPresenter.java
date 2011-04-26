/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.cristal.storm.prototype.client.mvp.view.CompanyUiHandlers;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

import com.google.gwt.event.shared.EventBus;

/**
 * Company Presenter implementation
 * This is the Presenter handling the company information
 */
public class CompanyPresenter extends
        Presenter<CompanyPresenter.CompanyViewInterface, CompanyPresenter.CompanyProxy>
        implements CompanyUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "company";

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link CompanyPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface CompanyProxy extends Proxy<CompanyPresenter>, Place {
    }

    /**
     * {@link CompanyPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface CompanyViewInterface extends View,
            HasUiHandlers<CompanyUiHandlers> {
    }

    /*
     * This interface is the link between the view and the presenter.
     * The presenter has to implement this interface.
     * This interface should be declared separetly.
    public interface CompanyUiHandlers extends UiHandlers {
    }
     */

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public CompanyPresenter(EventBus eventBus, CompanyViewInterface view,
            CompanyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
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
        RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, 
                this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}
