/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.view;

import com.cristal.storm.prototype.client.mvp.presenter.TimesheetPresenter.TimesheetViewInterface;
import com.cristal.storm.prototype.client.util.Resources;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Timesheet Presenter's view
 *
 */
public class TimesheetView extends ViewWithUiHandlers<TimesheetUiHandlers> implements
        TimesheetViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static TimesheetViewUiBinder uiBinder = GWT
            .create(TimesheetViewUiBinder.class);

    /*
     * @UiField annotaded vars. can be used here from your ui.xml template
     */
    @UiField
    SimplePanel simplePanel;

    private final Widget widget;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface TimesheetViewUiBinder extends UiBinder<Widget, TimesheetView> {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public TimesheetView() {
        widget = uiBinder.createAndBindUi(this);
        
        //simplePanel.add(timesheetView.asWidget());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        simplePanel.clear();

        if (content != null) {
            HTMLPanel dynContent = new HTMLPanel(Resources.INSTANCE.synchronous().getText());
            dynContent.add(content, "dynContent");
            
            simplePanel.add(dynContent);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}

