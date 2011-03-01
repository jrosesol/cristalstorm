/**
 *
 *
 * @author Jose Rose
 * Dec 15, 2010
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.cristal.storm.prototype.client.mvp.view.AppStartPageUiHandlers;

/**
 * AppStartPage Presenter implementation
 * This is the first entry to the Application creation point.
 */
public class AppStartPagePresenter extends
        Presenter<AppStartPagePresenter.AppStartPageViewInterface, AppStartPagePresenter.AppStartPageProxy> implements
        AppStartPageUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "main";
    
    private final PlaceManager placeManager;
    
    private final DispatchAsync dispatcher;
    
    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetTabContent = new Type<RevealContentHandler<?>>();

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link AppStartPagePresenter}'s proxy.
     */
    @ProxyStandard
    @NameToken(nameToken)
    public interface AppStartPageProxy extends Proxy<AppStartPagePresenter>, Place {
    }

    /**
     * {@link AppStartPagePresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface AppStartPageViewInterface extends View, HasUiHandlers<AppStartPageUiHandlers> {
        public String getUriText();
        public String getTagsText();
		void addToMCECollection(String uriText, String tagsText);
		void tagCollectionFilter(String filter);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public AppStartPagePresenter(final EventBus eventBus,
            final AppStartPageViewInterface view,
            final AppStartPageProxy proxy, final PlaceManager placeManager,
            final DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
        getView().setUiHandlers(this);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void revealInParent() {
        RevealRootLayoutContentEvent.fire(this, this);
    }

    @Override
    public void onStormit() {
        getView().addToMCECollection(getView().getUriText(),getView().getTagsText());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}