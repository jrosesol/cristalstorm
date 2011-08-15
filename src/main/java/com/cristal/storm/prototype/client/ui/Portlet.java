/*
 * Copyright 2010 The gwtquery plugins team.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cristal.storm.prototype.client.ui;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.client.i18n.UtilFunc;
import com.cristal.storm.prototype.client.mvp.presenter.ProjectPopupDetailsPresenter;
import com.cristal.storm.prototype.client.ui.WorkActivityView.ContentDisplayType;
import com.cristal.storm.prototype.shared.proxy.AccountProxy;
import com.cristal.storm.prototype.shared.proxy.ActivityProxy;
import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.cristal.storm.prototype.shared.service.CommandWatchDog;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.AccountRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.ActivityListRequestContext;
import com.cristal.storm.prototype.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import gwtquery.plugins.draggable.client.events.BeforeDragStartEvent;
import gwtquery.plugins.draggable.client.events.DragStopEvent;
import gwtquery.plugins.draggable.client.events.BeforeDragStartEvent.BeforeDragStartEventHandler;
import gwtquery.plugins.draggable.client.events.DragStopEvent.DragStopEventHandler;
import gwtquery.plugins.draggable.client.gwt.DraggableWidget;

/**
 * Portlet widget
 * 
 * @author Julien Dramaix (julien.dramaix@gmail.com)
 * 
 * TODO: D&D, enable the drag and drop
 */
public class Portlet extends Composite /*DraggableWidget<Widget>*/ {
    
    interface PortletUiBinder extends UiBinder<Widget, Portlet> {
    }

    /**
     * This handler will modify the position css attribute of portlets during
     * the drag
     * 
     * @author Julien Dramaix (julien.dramaix@gmail.com)
     * 
     */
//    private static class DraggablePositionHandler implements BeforeDragStartEventHandler, DragStopEventHandler {
//
//        /**
//         * before that the drag operation starts, we will "visually" detach the
//         * draggable by setting it css position to absolute.
//         */
//        public void onBeforeDragStart(BeforeDragStartEvent event) {
//            // "detach" visually the element of the parent
//            $(event.getDraggable()).css("position", "absolute");
//
//        }
//
//        public void onDragStop(DragStopEvent event) {
//            // "reattach" the element
//            $(event.getDraggable()).css("position", "relative").css("top", null).css("left", null);
//
//        }
//    }

    // This handler is stateless
//    private static DraggablePositionHandler HANDLER = new DraggablePositionHandler();
    private static PortletUiBinder uiBinder = GWT.create(PortletUiBinder.class);

    @UiField
    FocusPanel portletFocus;
    
    @UiField
    SimplePanel timeEntryContent;
        
    private final TimeEntryProxy portletTimeEntry;
    
    private Provider<AppsConstants> appCteProvider;
    
    @Inject
    public Portlet(TimeEntryProxy timeEntry, final Provider<AppsConstants> appCteProvider) {
        initWidget(uiBinder.createAndBindUi(this));
        setup();
        
        this.appCteProvider = appCteProvider;
        
        this.portletTimeEntry = timeEntry;
    }
    
    public void setHandlers(final CommandWatchDog commandWatchDog, final DataStoreProxy dataStoreProxy) {
        
        timeEntryContent.add(new WorkActivityView(appCteProvider, ContentDisplayType.VIEWABLE, dataStoreProxy, portletTimeEntry));
        
        // Create a basic popup widget
        final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
        simplePopup.ensureDebugId("cwBasicPopup-simplePopup");
        simplePopup.setWidth("150px");
        simplePopup.setWidget(new WorkActivityView(appCteProvider, ContentDisplayType.EDITABLE, dataStoreProxy, portletTimeEntry));

        portletFocus.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {

                if (event.getNativeKeyCode() == 13) {
                    // Reposition the popup relative to the button
                    Widget source = (Widget) event.getSource();
                    int left = source.getAbsoluteLeft() + 20;
                    int top = source.getAbsoluteTop() + 20;
                    simplePopup.setPopupPosition(left, top);                    
                    simplePopup.setAutoHideEnabled(true);
                    simplePopup.setGlassEnabled(true);

                    // Show the popup
                    simplePopup.show();
                }
            }
        });
        
        portletFocus.addDoubleClickHandler(new DoubleClickHandler() {

            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                // Reposition the popup relative to the button
                Widget source = (Widget) event.getSource();
                int left = source.getAbsoluteLeft() + 20;
                int top = source.getAbsoluteTop() + 20;
                simplePopup.setPopupPosition(left, top);                    
                simplePopup.setAutoHideEnabled(true);
                simplePopup.setGlassEnabled(true);

                // Show the popup
                simplePopup.show();                
            }
            
        });
    }
    
    // TODO: D&D, enable the drag and drop
    private void setup() {
        // opacity of the portlet during the drag
        //setDraggingOpacity(new Float(0.8));
        // zIndex of the portlet during the drag
        //setDraggingZIndex(1000);
        // add position handler
        //addBeforeDragHandler(HANDLER);
        //addDragStopHandler(HANDLER);
    }
}