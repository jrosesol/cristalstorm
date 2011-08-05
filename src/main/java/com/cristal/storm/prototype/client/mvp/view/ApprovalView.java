/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.view;


import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.client.mvp.presenter.ApprovalPresenter.TasksViewInterface;
import com.cristal.storm.prototype.client.util.Resources;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Tasks Presenter's view
 *
 */
public class ApprovalView extends ViewWithUiHandlers<ApprovalUiHandlers> implements
        TasksViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static ApprovalViewUiBinder uiBinder = GWT
            .create(ApprovalViewUiBinder.class);

    /*
     * @UiField annotaded vars. can be used here from your ui.xml template
     */
    @UiField
    SimplePanel simplePanel;
    
    @UiField
    PushButton push1;
    
    @UiField
    PushButton push2;
    
    

    private final Widget widget;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface ApprovalViewUiBinder extends UiBinder<Widget, ApprovalView> {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public ApprovalView() {
        widget = uiBinder.createAndBindUi(this);
        
        //HTMLPanel dynContent = new HTMLPanel(Resources.INSTANCE.synchronous().getText());
        //dynContent.add(new Label("This content is dynamically generated."), "day_content");
        
        //simplePanel.add(dynContent); 
        
        AppsConstants lConstants = (AppsConstants) GWT.create(AppsConstants.class);
        
        
        push1.setText(lConstants.save());
        push2.setText(lConstants.close());
         
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
