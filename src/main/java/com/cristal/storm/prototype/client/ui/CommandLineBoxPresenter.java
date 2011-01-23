/**
 *
 *
 * @author Jose Rose
 * Dec 25, 2010
 */
package com.cristal.storm.prototype.client.ui;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealRootPopupContentEvent;

/**
 * CommandLineBox Presenter implementation
 * This widget is a Command Line Box to get commands from the user
 */
public class CommandLineBoxPresenter extends
        Presenter<CommandLineBoxPresenter.CommandLineBoxViewInterface, CommandLineBoxPresenter.CommandLineBoxProxy>
        implements CommandLineBoxUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "command_line";
    private final EventBus eventBus;
    final CommandDigester commandDigester;
    private final CommandHandlerPresenter commandHandlerPresenter;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link CommandLineBoxPresenter}'s proxy.
     */
    @ProxyStandard
    @NameToken(nameToken)
    public interface CommandLineBoxProxy extends Proxy<CommandLineBoxPresenter>, Place {
    }

    /**
     * {@link CommandLineBoxPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface CommandLineBoxViewInterface extends View,
            HasUiHandlers<CommandLineBoxUiHandlers> {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public CommandLineBoxPresenter(EventBus eventBus,
            CommandLineBoxViewInterface view, CommandLineBoxProxy proxy,
            PlaceManager placeManager, DispatchAsync dispatcher,
            final CommandHandlerPresenter commandHandlerPresenter,
            final CommandDigester commandDigester) {
        super(eventBus, view, proxy);
        getView().setUiHandlers(this);
        this.eventBus = eventBus;
        this.commandHandlerPresenter = commandHandlerPresenter;
        this.commandDigester = commandDigester;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    @Override
    public void onShowCommandView(String command) {
        commandDigester.parseCommand(command);
        
        RevealRootPopupContentEvent.fire(this, commandHandlerPresenter);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}