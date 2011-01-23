/**
 *
 *
 * @author Jose Rose
 * Dec 15, 2010
 */
package com.cristal.storm.prototype.client.mvp.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.cristal.storm.prototype.client.mvp.presenter.AppStartPagePresenter.AppStartPageViewInterface;
import com.cristal.storm.prototype.client.ui.CommandLineBoxView;


/**
 * AppStartPage Presenter's view
 *
 */
public class AppStartPageView extends ViewWithUiHandlers<AppStartPageUiHandlers> implements
        AppStartPageViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static AppStartPageViewUiBinder uiBinder = GWT
            .create(AppStartPageViewUiBinder.class);

    /*
     * @UiField annotated vars. can be used here from your ui.xml template
     */
    @UiField
    SimplePanel simplePanel;
    
    @UiField
    VerticalPanel mainLayout;

    private final Widget widget;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface AppStartPageViewUiBinder extends UiBinder<Widget, AppStartPageView> {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public AppStartPageView(TopView topView, CommandLineBoxView commandLineBox) {
        widget = uiBinder.createAndBindUi(this);

        mainLayout.add(commandLineBox.asWidget());
        simplePanel.add(topView.asWidget());
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
    
    @UiHandler("contentA")
    void onSaveButtonClicked(ClickEvent event) {
      if (getUiHandlers() != null) {
        getUiHandlers().onContentA();
      }
    }

    @UiHandler("contentB")
    void onSaveButtonClicked1(ClickEvent event) {
      if (getUiHandlers() != null) {
        getUiHandlers().onContentB();
      }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
