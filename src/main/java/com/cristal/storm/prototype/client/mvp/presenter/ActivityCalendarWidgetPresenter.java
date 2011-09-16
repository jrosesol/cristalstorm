/**
 *
 *
 * @author Jose Rose
 * 2011-07-25
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickHandler;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.event.CreateTimeEntryEvent;
import com.cristal.storm.prototype.client.event.CreateTimeEntryEvent.CreateTimeEntryHandler;
import com.cristal.storm.prototype.client.event.RemovePortletsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.client.mvp.view.ActivityCalendarWidgetUiHandlers;
import com.cristal.storm.prototype.client.mvp.view.CompanyUiHandlers;
import com.cristal.storm.prototype.client.ui.Portlet;
import com.cristal.storm.prototype.client.ui.PortletPlaceholder;
import com.cristal.storm.prototype.client.util.Resources;
import com.cristal.storm.prototype.client.util.UtilFunc;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.CommandWatchDog;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;

/**
 * ActivityCalendarWidget Presenter implementation
 * Handles the calendar like widget to display time entries for a given week. This is the main interface where we
 */
public class ActivityCalendarWidgetPresenter extends
        PresenterWidget<ActivityCalendarWidgetPresenter.ActivityCalendarWidgetViewInterface> implements ActivityCalendarWidgetUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    private EventBus eventBus;
    
    @Inject
    DataStoreProxy dataStoreProxy;
    
    @Inject
    CommandWatchDog commandWatchDog;
    
    @Inject
    Provider<AppsConstants> appCteProvider;
    
    final TimeEntryWizardPopupPresenter timeEntryWizPopup;
    
    private final Provider<TimeEntryRequestContext> timeEntryContextProvider;
    
    private Date widgetDate;    

    /** Uniquely represent the view to distinguish it when serving events. */
    public final long VIEW_UID = UtilFunc.generateUID(); 

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link ActivityCalendarWidgetPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface ActivityCalendarWidgetViewInterface extends View, HasUiHandlers<ActivityCalendarWidgetUiHandlers> {
        public void addPortlet(Portlet p);
        public void addPlaceholder(PortletPlaceholder placeholder);
        public void clearPortlets();
        public void setDateDisplay(String dateToSet);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public ActivityCalendarWidgetPresenter(EventBus eventBus, ActivityCalendarWidgetViewInterface view,
            Provider<TimeEntryRequestContext> timeEntryContextProvider, final TimeEntryWizardPopupPresenter timeEntryWizPopup) {
        super(eventBus, view);
        getView().setUiHandlers(this);
        
        this.eventBus = eventBus;
        widgetDate = null;
        this.timeEntryContextProvider = timeEntryContextProvider;
        this.timeEntryWizPopup = timeEntryWizPopup;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * This is the handler when the user clicks the create time entry.
     * @return The handler
     */
    private ClickHandler onClickPlaceholder() {
        return new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                
                timeEntryWizPopup.setEventSourceUID(VIEW_UID);
                timeEntryWizPopup.getView().center();
                timeEntryWizPopup.getView().show();
            }
        };
    }
    
    private CreateTimeEntryHandler onTimeEntryWizardOkButtonClicked() {
        return new CreateTimeEntryHandler() {

            @Override
            public void onCreateTimeEntry(CreateTimeEntryEvent event) {
                
                // Only if the event is set for us
                if (event.getVIEW_UID() == VIEW_UID) {                    
                    Portlet aPortlet = new Portlet(event.getTimeEntry(), appCteProvider);
                    aPortlet.setHandlers(commandWatchDog, dataStoreProxy);
                    getView().addPortlet(aPortlet);
                }
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    protected void onBind() {
        
        // Register a event for the new time entry ok button wizard
        registerHandler(eventBus.addHandler(CreateTimeEntryEvent.getType(), onTimeEntryWizardOkButtonClicked()));
        
        // Register the data available handler
        registerHandler(eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {

            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent,
                    DATA_EVENT_TYPE eventType) {
                if (eventType == DATA_EVENT_TYPE.DATA_IS_AVAILABLE) {
                    
                    List<TimeEntryProxy> timeEntryList = dataStoreProxy.getTimeEntryData();
                    
                    for (TimeEntryProxy timeEntry : timeEntryList) {
                        Portlet aPortlet = new Portlet(timeEntry, appCteProvider);
                        aPortlet.setHandlers(commandWatchDog, dataStoreProxy);
                        
                        if (widgetDate != null) {
                            if (widgetDate.getDate() == timeEntry.getTimeEntryTimestamp().getDate() &&
                                    widgetDate.getMonth() == timeEntry.getTimeEntryTimestamp().getMonth() &&
                                    widgetDate.getYear() == timeEntry.getTimeEntryTimestamp().getYear()) {
                                getView().addPortlet(aPortlet);                                
                            }
                        }
                        else {
                            Log.info("Set the widget date");
                            throw new IllegalArgumentException();
                        }
                    }

                    // TODO : Set a global Date formatter
                    DateTimeFormat dateFormatter = DateTimeFormat.getFormat("E, dd/MM");
                    getView().setDateDisplay(dateFormatter.format(widgetDate));

                    // Add placeholders
                    PortletPlaceholder placeholder = createPlaceholder();
                    registerHandler(placeholder.addClickHandler(onClickPlaceholder()));
                    getView().addPlaceholder(placeholder);
                }
            }
        }));
        
        registerHandler(eventBus.addHandler(RemovePortletsEvent.getType(), new RemovePortletsEvent.RemovePortletsHandler() {
            
            @Override
            public void onRemovePortlets(RemovePortletsEvent event) {
                getView().clearPortlets();                
            }
        }));
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    protected void onReveal() {
        super.onReveal();
    }
    
    @Override
    public void setInSlot(Object slot, PresenterWidget<?> content) {
        super.setInSlot(slot, content);
    }
    
    @Override
    protected void onHide() {
        super.onHide();
        
        getView().clearPortlets();
    }
    
    /**
     * Set the date the widget will be listening to. Only if a time entry is
     * on the same date will this widget display it.
     * @param widgetDate Date to which this widget will respond.
     */
    public void setCalendarDate(Date widgetDate) {
        this.widgetDate = widgetDate;
    }

    @Override
    public void onClickAddTimeEntry() {
        
    }
    
    /**
     * A place holder for the time entries (to tell a user to click somewhere)
     * @return 
     */
    private PortletPlaceholder createPlaceholder() {
        PortletPlaceholder placeholder = new PortletPlaceholder();
        
        return placeholder;
        
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}
