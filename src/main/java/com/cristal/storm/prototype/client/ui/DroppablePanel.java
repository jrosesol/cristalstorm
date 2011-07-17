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

import com.cristal.storm.prototype.client.util.Resources;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

/**
 * TODO: Add comments for DroppablePanel
 * 
 */
public class DroppablePanel extends DroppableWidget<FlowPanel> {
    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////

    private FlowPanel innerPanel;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    public DroppablePanel() {
        init();
        initWidget(innerPanel);
        setupDrop();
        
    }

    // /////////////////////////////////////////////////////////////////////////
    // Handlers
    // /////////////////////////////////////////////////////////////////////////
    
    public void setHandlers(final EventBus eventBus) {
        // Add place holders
        SimplePanel amPlaceHolder = new SimplePanel();
        amPlaceHolder.addStyleName(Resources.INSTANCE.style().placeHolder());
        amPlaceHolder.setHeight("50px");
        amPlaceHolder.setWidth("50px");
        innerPanel.add(amPlaceHolder);
        
        Portlet aPortlet = new Portlet("Title", "Content");
        aPortlet.setHandlers(eventBus);
        add(aPortlet);
        
        // Add place holders
        SimplePanel pmPlaceHolder = new SimplePanel();
        pmPlaceHolder.addStyleName(Resources.INSTANCE.style().placeHolder());
        pmPlaceHolder.setHeight("50px");
        pmPlaceHolder.setWidth("50px");
        innerPanel.add(pmPlaceHolder);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    public void add(Portlet p) {
        innerPanel.add(p);
    }

    private void init() {
        innerPanel = new FlowPanel();
        innerPanel.addStyleName(Resources.INSTANCE.style().sortablePanel());
    }

    /**
     * Register drop handler !
     */
    private void setupDrop() {
        SortableDragAndDropHandler sortableHandler = new SortableDragAndDropHandler(innerPanel);
        addDropHandler(sortableHandler);
        addOutDroppableHandler(sortableHandler);
        addOverDroppableHandler(sortableHandler);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}
