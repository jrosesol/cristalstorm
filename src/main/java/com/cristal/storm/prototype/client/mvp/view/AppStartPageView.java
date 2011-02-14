/**
 *
 *
 * @author Jose Rose
 * Dec 15, 2010
 */
package com.cristal.storm.prototype.client.mvp.view;

import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.DraggableOptions.HelperType;
import gwtquery.plugins.droppable.client.DroppableOptions.DroppableTolerance;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;
import gwtquery.plugins.droppable.client.gwt.DragAndDropCellList;
import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellList.Resources;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
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
    public Button retrieveButton;

    @UiField
    public TextBox docUDIBox;
    
    @UiField
    public TextBox repoUIDBox;
    
    @UiField
    public TextBox testRepoURI;

    private final Widget widget;

    private static Resources DEFAULT_RESOURCES = GWT.create(Resources.class);
    
    // Dialog box
    private final DialogBox dialogBox;
    private final VerticalPanel widgetContainer;
    private final HTML dialogHtmlResponse;
    
    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface AppStartPageViewUiBinder extends UiBinder<Widget, AppStartPageView> {
    }
    

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public AppStartPageView(CommandLineBoxView commandLineBox) {
        widget = uiBinder.createAndBindUi(this);

        widgetContainer = new VerticalPanel();
        dialogHtmlResponse = new HTML();
        
        dialogHtmlResponse.setWidth("800px");

        dialogBox = createDialogBox();
        dialogBox.setWidget(widgetContainer);
        widgetContainer.setWidth("800px");
        dialogBox.setWidth("800px");
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public String getDocUID() {
        // TODO Auto-generated method stub
        return docUDIBox.getText();
    }

    @Override
    public String getRepoUID() {
        // TODO Auto-generated method stub
        return repoUIDBox.getText();
    }

    @Override
    public String getRepoURI() {
        // TODO Auto-generated method stub
        return testRepoURI.getText();
    }

    @Override
    public void setServerResponse(String serverResponse) {
        dialogHtmlResponse.setHTML(serverResponse);
    }

    @Override
    public void showServerResponseMessage() {
        dialogBox.setWidth("800px");
        dialogBox.show();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    @UiHandler("retrieveButton")
    public void onRetrieveButtonClicked(ClickEvent event) {
        if (getUiHandlers() != null) {
            getUiHandlers().onRetrieveDocumentSet();
        }
    }

    private DialogBox createDialogBox() {
        // Create a dialog box and set the caption text
        final DialogBox dialogBox = new DialogBox();
        dialogBox.ensureDebugId("cwDialogBox");
        dialogBox.setText("XDS.b Retrieve Document Set");
        dialogBox.hide();
        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);  
        dialogBox.center();

        // Create a table to layout the content
        widgetContainer.add(dialogHtmlResponse);

        // Add a close button at the bottom of the dialog
        Button closeButton = new Button(
            "Close", new ClickHandler() {
              public void onClick(ClickEvent event) {
                dialogBox.hide();
              }
            });
        widgetContainer.add(closeButton);

        // Return the dialog box
        return dialogBox;
    }


    
    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
