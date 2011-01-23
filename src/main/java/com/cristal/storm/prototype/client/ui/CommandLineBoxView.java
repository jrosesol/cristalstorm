/**
 *
 *
 * @author Jose Rose
 * Dec 25, 2010
 */
package com.cristal.storm.prototype.client.ui;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * CommandLineBox Presenter's view
 *
 */
public class CommandLineBoxView extends ViewWithUiHandlers<CommandLineBoxUiHandlers> implements
        CommandLineBoxPresenter.CommandLineBoxViewInterface, HasWidgets {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static CommandLineBoxViewUiBinder uiBinder = GWT
            .create(CommandLineBoxViewUiBinder.class);

    /*
     * @UiField annotaded vars. can be used here from your ui.xml template
     */
    @UiField
    TextBox userCommand;

    private final Widget widget;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface CommandLineBoxViewUiBinder extends UiBinder<Widget, CommandLineBoxView> {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public CommandLineBoxView() {
        widget = uiBinder.createAndBindUi(this);
        //initWidget(widget);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public void add(Widget w) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<Widget> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Widget w) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @UiHandler("LaunchCommand")
    void onSaveButtonClicked(ClickEvent event) {
      if (getUiHandlers() != null) {
        getUiHandlers().onShowCommandView(userCommand.getText());
      }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
