/**
 *
 *
 * @author Jose Rose
 * 2011-04-29
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.event.CreateTimeEntryEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.cristal.storm.prototype.client.ui.Portlet;
import com.cristal.storm.prototype.shared.proxy.DomainTimeCodesProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
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
    private final DataStoreProxy dataStoreProxy;

    // TODO : Find a better way to do this... 
    private int eventSourceUID;
    
    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link TimeEntryWizardPopupPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface TimeEntryWizardPopupViewInterface extends PopupView {
        public HasClickHandlers onWizardOkButton();
        public int getSelectedTimeCodeIdx();
        public void setTimeEntryCodes(List<String> domainTimeCodes);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public TimeEntryWizardPopupPresenter(EventBus eventBus, TimeEntryWizardPopupViewInterface view, DataStoreProxy dataStoreProxy) {
        super(eventBus, view);
        
        this.eventBus = eventBus;
        this.dataStoreProxy = dataStoreProxy;
        

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
                // Add the possible time codes
                TimeCodeType selectedTimeCode = TimeCodeType.NORMAL;
                for (DomainTimeCodesProxy domainTimeCodesProxy : dataStoreProxy.getDomainTimeCodesProxy()) {

                    List<TimeCodeType> timeCodes = domainTimeCodesProxy.getTimeCodeTypes();
                    
                    selectedTimeCode = timeCodes.get(getView().getSelectedTimeCodeIdx());
                }
                
                CreateTimeEntryEvent.fire(eventBus, getEventSourceUID(), selectedTimeCode);
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
        
    @Override
    protected void onBind() {
        super.onBind();
        
        registerHandler(getView().onWizardOkButton().addClickHandler(onWizardOkButton()));
        
        registerHandler(eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {
            
            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent,
                    DATA_EVENT_TYPE eventType) {
                if (eventType == DATA_EVENT_TYPE.REVEAL_PRESENTERS) {
                    List<String> timeCodesList = new ArrayList<String>();
                    
                    // Add the possible time codes
                    for (DomainTimeCodesProxy domainTimeCodesProxy : dataStoreProxy.getDomainTimeCodesProxy()) {

                        List<TimeCodeType> timeCodes = domainTimeCodesProxy.getTimeCodeTypes();
                        
                        for (TimeCodeType timeCodeType : timeCodes) {
                            timeCodesList.add(com.cristal.storm.prototype.client.i18n.UtilFunc.getTimeCodeValue(timeCodeType));
                        }
                    }
                    
                    getView().setTimeEntryCodes(timeCodesList);
                }
            }
        }));
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
