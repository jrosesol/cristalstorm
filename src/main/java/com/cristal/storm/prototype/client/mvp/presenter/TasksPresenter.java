/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.cristal.storm.prototype.client.mvp.view.TasksUiHandlers;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

import com.google.gwt.event.shared.EventBus;

/**
 * Tasks Presenter implementation
 * Handles the task.
 */
public class TasksPresenter extends
        Presenter<TasksPresenter.TasksViewInterface, TasksPresenter.TasksProxy>
        implements TasksUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "tasks";

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link TasksPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface TasksProxy extends Proxy<TasksPresenter>, Place {
    }

    /**
     * {@link TasksPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface TasksViewInterface extends View,
            HasUiHandlers<TasksUiHandlers> {
    }

    /*
     * This interface is the link between the view and the presenter.
     * The presenter has to implement this interface.
     * This interface should be declared separetly.
    public interface TasksUiHandlers extends UiHandlers {
    }
     */

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public TasksPresenter(EventBus eventBus, TasksViewInterface view,
            TasksProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
        getView().setUiHandlers(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, 
                this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}