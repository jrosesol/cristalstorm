/**
 *
 *
 * @author Jose Rose
 * Dec 26, 2010
 */
package com.cristal.storm.prototype.client.ui;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * CommandHandler Presenter implementation This class allows to present a dialog
 * with simplified commands
 */
public class CommandHandlerPresenter extends
        PresenterWidget<CommandHandlerPresenter.CommandHandlerViewInterface>
        implements NavigationHandler {

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "command_view";
    
    final CommandDigester commandDigester;
    private final PatientRequiredInfoPresenterWidget patientRequiredInfoWidget;
    private final PatientSinglePrescriptionPresenterWidget patientSinglePrescriptionWidget;

    // /////////////////////////////////////////////////////////////////////////
    // Interfaces
    // /////////////////////////////////////////////////////////////////////////

    /**
     * {@link CommandHandlerPresenter}'s view.
     */
    public interface CommandHandlerViewInterface extends PopupView {
        public void addContentWidget(Widget child);
        public void clearContent();
    }

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    @Inject
    public CommandHandlerPresenter(final EventBus eventBus,
            final CommandHandlerViewInterface view,
            final CommandDigester commandDigester,
            final PatientRequiredInfoPresenterWidget patientRequiredInfoWidget,
            final PatientSinglePrescriptionPresenterWidget patientSinglePrescriptionWidget) {
        super(eventBus, view);
        this.commandDigester = commandDigester;
        this.patientRequiredInfoWidget = patientRequiredInfoWidget;
        this.patientSinglePrescriptionWidget = patientSinglePrescriptionWidget;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Handlers
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////

    @Override
    public void onNavigation(NavigationEvent navigationEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onReveal() {
        
        Map<Integer, String> commandMap = commandDigester.getLastCommand();
        
        Iterator<Integer> commandKeysItr = commandMap.keySet().iterator();

        while (commandKeysItr.hasNext()) {
            String curCommand = commandMap.get(commandKeysItr.next());
            
            if (curCommand.compareTo("info") == 0) {
                getView().addContentWidget(
                        patientRequiredInfoWidget.getView().asWidget());
            }
            else if (curCommand.compareTo("pres") == 0) {
                getView().addContentWidget(
                        patientSinglePrescriptionWidget.getView().asWidget());
            }
        }
    }
    
    @Override
    public void onHide() {
        getView().clearContent();
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    // /////////////////////////////////////////////////////////////////////////

}