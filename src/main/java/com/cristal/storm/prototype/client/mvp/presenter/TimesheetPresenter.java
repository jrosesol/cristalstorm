/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.cristal.storm.prototype.client.mvp.view.TimesheetUiHandlers;
import com.cristal.storm.prototype.client.util.Resources;
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

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Timesheet Presenter implementation
 * This will handle all timesheet actions
 */
public class TimesheetPresenter extends
        Presenter<TimesheetPresenter.TimesheetViewInterface, TimesheetPresenter.TimesheetProxy>
        implements TimesheetUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "timesheet";

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link TimesheetPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface TimesheetProxy extends Proxy<TimesheetPresenter>, Place {
    }

    /**
     * {@link TimesheetPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface TimesheetViewInterface extends View,
            HasUiHandlers<TimesheetUiHandlers> {
    }

    /*
     * This interface is the link between the view and the presenter.
     * The presenter has to implement this interface.
     * This interface should be declared separetly.
    public interface TimesheetUiHandlers extends UiHandlers {
    }
     */

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public TimesheetPresenter(EventBus eventBus, TimesheetViewInterface view,
            TimesheetProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
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