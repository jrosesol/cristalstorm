/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.view;

import com.cristal.storm.prototype.client.mvp.presenter.ReportsPresenter.ReportsViewInterface;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Reports Presenter's view
 *
 */
public class ReportsView extends ViewWithUiHandlers<ReportsUiHandlers> implements
        ReportsViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static ReportsViewUiBinder uiBinder = GWT
            .create(ReportsViewUiBinder.class);

    /*
     * @UiField annotaded vars. can be used here from your ui.xml template
     */
    //@UiField
    //SimplePanel simplePanel;

    private final Widget widget;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface ReportsViewUiBinder extends UiBinder<Widget, ReportsView> {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public ReportsView() {
        widget = uiBinder.createAndBindUi(this);
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
