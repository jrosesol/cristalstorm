/**
 *
 *
 * @author Jose Rose
 * Dec 26, 2010
 */
package com.cristal.storm.prototype.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.cristal.storm.prototype.client.mvp.presenter.*;

/**
 * CommandHandler Presenter's view
 * 
 */
public class CommandHandlerView extends PopupViewImpl implements
        CommandHandlerPresenter.CommandHandlerViewInterface {

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    private final DialogBox dialogBox;
    private final VerticalPanel dialogContentWidgets;
    private final VerticalPanel dialogContent;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    @Inject
    public CommandHandlerView(EventBus eventBus,
            final VerticalPanel dialogContentWidgets,
            final VerticalPanel dialogContent) {
        super(eventBus);
        this.dialogContentWidgets = dialogContentWidgets;
        this.dialogContent = dialogContent;

        dialogBox = createDialogBox();
        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);

        // simple = new Dialog();
        // simple.setHeading("Dialog Test");
        // simple.setButtons(Dialog.OKCANCEL);
        // simple.setBodyStyleName("pad-text");
        // simple.setScrollMode(Scroll.AUTO);
        // simple.setHideOnButtonClick(true);
        // simple.setModal(true);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return dialogBox;
    }

    @Override
    public void addContentWidget(Widget child) {
        dialogContentWidgets.add(child);
        //dialogContentWidgets.add(new Button());
        //dialogContentWidgets.layout();
    }
    
    @Override
    public void clearContent() {
        dialogContentWidgets.clear();
    }
    
    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    public void setCommand(String command) {
        // simple.addText(command);
    }

    public void showDialog() {
        // simple.show();
    }

    /**
     * Create the dialog box for this example.
     * 
     * @return the new dialog box
     */
    private DialogBox createDialogBox() {
        // Create a dialog box and set the caption text
        final DialogBox dialogBox = new DialogBox();
        dialogBox.ensureDebugId("cwDialogBox");
        dialogBox.setText("Hello world");

        // Create a table to layout the content
        dialogBox.setWidget(dialogContent);
        dialogContent.add(dialogContentWidgets);

        // Add a close button at the bottom of the dialog
        Button closeButton = new Button(
            "Close", new ClickHandler() {
              public void onClick(ClickEvent event) {
                dialogBox.hide();
              }
            });
        dialogContent.add(closeButton);

        // Return the dialog box
        return dialogBox;
    }


    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}
