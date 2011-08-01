/**
 *
 *
 * @author Jose Rose
 * 2011-07-25
 */
package com.cristal.storm.prototype.client.ui;

import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.event.RemovePortletsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.cristal.storm.prototype.client.mvp.presenter.ProjectPopupDetailsPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.TimesheetCellListPresenter;
import com.cristal.storm.prototype.client.mvp.presenter.ProjectPopupDetailsPresenter.ProjectPopupDetailsViewInterface;
import com.cristal.storm.prototype.client.mvp.presenter.TimesheetPresenter;
import com.cristal.storm.prototype.client.util.Resources;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.CommandWatchDog;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * ActivityCalendarWidget Presenter implementation
 * Handles the calendar like widget to display time entries for a given week. This is the main interface where we
 */
public class ActivityCalendarWidgetPresenter extends
        PresenterWidget<ActivityCalendarWidgetPresenter.ActivityCalendarWidgetViewInterface> {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    private EventBus eventBus;
    
    @Inject
    DataStoreProxy dataStoreProxy;
    @Inject
    CommandWatchDog commandWatchDog;
    
    private Date widgetDate;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link ActivityCalendarWidgetPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface ActivityCalendarWidgetViewInterface extends View {
        public void addProtlet(Widget portlet);
        public void clearPortlets();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public ActivityCalendarWidgetPresenter(EventBus eventBus, ActivityCalendarWidgetViewInterface view) {
        super(eventBus, view);        
        this.eventBus = eventBus;
        widgetDate = null;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    protected void onBind() {
        registerHandler(eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {

            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent,
                    DATA_EVENT_TYPE eventType) {
                if (eventType == DATA_EVENT_TYPE.DATA_IS_AVAILABLE) {
                    
                    List<TimeEntryProxy> timeEntryList = dataStoreProxy.getTimeEntryData();
                    
                    for (TimeEntryProxy timeEntry : timeEntryList) {
                        Portlet aPortlet = new Portlet("", "Content");
                        aPortlet.setHandlers(eventBus, commandWatchDog, dataStoreProxy, timeEntry);
                        
                        if (widgetDate != null) {
                            if (widgetDate.getDate() == timeEntry.getTimeEntryTimestamp().getDate() &&
                                    widgetDate.getMonth() == timeEntry.getTimeEntryTimestamp().getMonth() &&
                                    widgetDate.getYear() == timeEntry.getTimeEntryTimestamp().getYear()) {
                                getView().addProtlet(aPortlet);                                
                            }
                        }
                        else {
                            System.out.print("Set the widget date");
                            assert(false);
                        }
                    }

                    // Add place holders
                    SimplePanel amPlaceHolder = new SimplePanel();
                    amPlaceHolder.addStyleName(Resources.INSTANCE.style().placeHolder());
                    amPlaceHolder.setHeight("50px");
                    amPlaceHolder.setWidth("50px");
                    getView().addProtlet(amPlaceHolder);
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

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}
