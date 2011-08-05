/**
 *
 *
 * @author Jose Rose
 * 2011-04-29
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.client.event.CreateTimeEntryEvent;
import com.cristal.storm.prototype.client.ui.Portlet;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HasHandlers;

/**
 * ProjectPopupDetails Presenter implementation
 * Handles the actions possible on the site
 */
public class TimeEntryWizardPopupPresenter extends
    PresenterWidget<TimeEntryWizardPopupPresenter.TimeEntryWizardPopupViewInterface> {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    private final EventBus eventBus;

    private int eventSourceUID;
    
    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetPopupContent = new Type<RevealContentHandler<?>>();

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link TimeEntryWizardPopupPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface TimeEntryWizardPopupViewInterface extends PopupView {
        public HasClickHandlers onWizardOkButton();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public TimeEntryWizardPopupPresenter(EventBus eventBus, TimeEntryWizardPopupViewInterface view, TimesheetCellListPresenter timesheetList) {
        super(eventBus, view);
        
        this.eventBus = eventBus;        
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * This is the handler when the user clicks the OK button for the create time entry dialog.
     * @return The handler
     */
    private ClickHandler onWizardOkButton() {
        return new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                CreateTimeEntryEvent.fire(eventBus, getEventSourceUID());
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    protected void onReveal() {
        this.setInSlot(TYPE_SetPopupContent, null);
    }
    
    @Override
    protected void onBind() {
        super.onBind();
        
        registerHandler(getView().onWizardOkButton().addClickHandler(onWizardOkButton()));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////
    
    public void setEventSourceUID(int eventSourceUID) {
        this.eventSourceUID = eventSourceUID;
    }

    public int getEventSourceUID() {
        return eventSourceUID;
    }

}
