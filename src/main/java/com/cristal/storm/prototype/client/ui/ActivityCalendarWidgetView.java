/**
 *
 *
 * @author Jose Rose
 * 2011-07-25
 */
package com.cristal.storm.prototype.client.ui;

import java.util.ArrayList;
import java.util.List;

import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.util.Resources;
import com.cristal.storm.prototype.client.util.UtilFunc;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.service.CommandWatchDog;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * ActivityCalendarWidget Presenter's view
 * 
 */
public class ActivityCalendarWidgetView extends DroppableWidget<FlowPanel> implements
        ActivityCalendarWidgetPresenter.ActivityCalendarWidgetViewInterface {

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    private static ActivityCalendarWidgetViewUiBinder uiBinder = GWT.create(ActivityCalendarWidgetViewUiBinder.class);

    private final Widget widget;

    private ActivityCalendarWidgetUiHandlers uiHandlers;

    @UiField
    protected FlowPanel someDay;

    @UiField
    protected FocusPanel dayFocusPanel;
    
    @UiField
    protected Label dateDisplay;
    
    // /////////////////////////////////////////////////////////////////////////
    // Interfaces
    // /////////////////////////////////////////////////////////////////////////

    interface ActivityCalendarWidgetViewUiBinder extends UiBinder<Widget, ActivityCalendarWidgetView> {
    }

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    public ActivityCalendarWidgetView() {
        
        widget = uiBinder.createAndBindUi(this);
        

        this.initWidget(widget);
        // weekFlowPanel.add(widget);

        // Initialize the weekdays

        init(someDay);
        setupDrop(someDay);
        // addPlaceHolder(someDay);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public void addToSlot(Object slot, Widget content) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeFromSlot(Object slot, Widget content) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setUiHandlers(ActivityCalendarWidgetUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    /**
     * Access the {@link UiHandlers} associated with this {@link View}.
     * <p>
     * <b>Important!</b> Never call {@link #getUiHandlers()} inside your
     * constructor since the {@link UiHandlers} are not yet set.
     * 
     * @return The {@link UiHandlers}, or {@code null} if they are not yet set.
     */
    protected ActivityCalendarWidgetUiHandlers getUiHandlers() {
        return uiHandlers;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    public void setHandlers(final EventBus eventBus, final CommandWatchDog commandWatchDog) {
    }

    public void addPortlet(Portlet p) {
        // Portlets are added before the placeholder
        int placeHolderIndex = someDay.getWidgetCount() - 1;
        
        if (placeHolderIndex < 0) {
            placeHolderIndex = 0;
        }
        
        someDay.insert(p, placeHolderIndex);
    }
    
    public void addPlaceholder(PortletPlaceholder placeholder) {
        someDay.add(placeholder);
    }

    private void init(final Widget weekdayFlowPanel) {
        weekdayFlowPanel.addStyleName(Resources.INSTANCE.style().sortablePanel());
    }

    /**
     * Register drop handler !
     * TODO: D&D, enable the drag and drop
     */
    private void setupDrop(final FlowPanel weekdayFlowPanel) {
//        SortableDragAndDropHandler sortableHandler = new SortableDragAndDropHandler(weekdayFlowPanel);
//        addDropHandler(sortableHandler);
//        addOutDroppableHandler(sortableHandler);
//        addOverDroppableHandler(sortableHandler);
    }

    @Override
    public void clearPortlets() {
        someDay.clear();        
    }

    @Override
    public void setDateDisplay(String dateToSet) {
        dateDisplay.setText(dateToSet);
    }
    
    @UiHandler("dayFocusPanel")
    public void onClickAddTimeEntry(ClickEvent event) {
        getUiHandlers().onClickAddTimeEntry();
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}
