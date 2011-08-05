/**
 * Copyright 2010 ArcBees Inc.
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

package com.cristal.storm.prototype.client.mvp.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import com.cristal.storm.prototype.client.AppsConstants;
import com.cristal.storm.prototype.client.mvp.presenter.MainPagePresenter;
import com.cristal.storm.prototype.client.util.Resources;
import com.google.gwt.user.cellview.client.CellTable;

/**
 * @author Jose Rose
 */
public class MainPageView extends ViewWithUiHandlers<MainPageUiHandlers>
        implements MainPagePresenter.MainPageViewInterface {

    private static MainPageViewUiBinder uiBinder = GWT
            .create(MainPageViewUiBinder.class);

    /*
     * @UiField annotated vars. can be used here from your ui.xml template
     */
    
//    @UiField
//    SimplePanel simpleContentHolder;
    
    @UiField HTMLPanel mainContent;
    
    @UiField Label lblTimesheet;    
    @UiField Label lblApproval;    
    @UiField Label lblReports;    
    @UiField Label lblCompany;
    @UiField Label lblLogout;
    @UiField Label lblSettings;
    @UiField SimplePanel panelSlogan;
    
    HTMLPanel htmlSlogan;

    private Widget widget;

    interface MainPageViewUiBinder extends UiBinder<Widget, MainPageView> {
        Widget createAndBindUi(MainPageView mainPageView);
    }

    @Inject
    public MainPageView() {
        widget = uiBinder.createAndBindUi(this);
        
        
        
//        mainContent = new HTMLPanel(Resources.INSTANCE.synchronous().getText());
//        simpleContentHolder.add(mainContent);
        
        AppsConstants lConstants = (AppsConstants) GWT.create(AppsConstants.class);
        
        lblTimesheet.setText(lConstants.timesheet());
        lblApproval.setText(lConstants.tasks());
        lblReports.setText(lConstants.reports());
        lblCompany.setText(lConstants.company());
        lblLogout.setText(lConstants.logout());
        lblSettings.setText(lConstants.settings());
        
        htmlSlogan = new HTMLPanel(lConstants.slogan());
        panelSlogan.add(htmlSlogan);
        
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (slot == MainPagePresenter.TYPE_SetMainContent) {
            setMainContent(content);
        } else {
            super.setInSlot(slot, content);
        }
    }

    private void setMainContent(Widget content) {
        mainContent.clear();
                

        if (content != null) {
            mainContent.add(content, "content");
        }
        

    }

    @Override
    public Widget asWidget() {
        return widget;
    }

}
