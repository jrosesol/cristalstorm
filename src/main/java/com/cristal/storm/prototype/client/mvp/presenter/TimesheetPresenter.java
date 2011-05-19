/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.mvp.view.TimesheetUiHandlers;
import com.cristal.storm.prototype.client.util.Resources;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
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
    
    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

    @Inject
    TimesheetCellListPresenter timesheetCellList;
    
    /*The project detail popup*/
    private final ProjectPopupDetailsPresenter projectDetailsPopup;

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
    public TimesheetPresenter(EventBus eventBus, TimesheetViewInterface view, TimesheetProxy proxy,
            PlaceManager placeManager, DispatchAsync dispatcher, final ProjectPopupDetailsPresenter projectPopup) {
        super(eventBus, view, proxy);
        getView().setUiHandlers(this);
        
        this.projectDetailsPopup = projectPopup;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onBind() {
        //super.bind();
        
        addRegisteredHandler( UpdateDataBindedObjectsEvent.getType(), new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {

            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent event) {
                addToPopupSlot(projectDetailsPopup);
            }
            
        } );
    };
    
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