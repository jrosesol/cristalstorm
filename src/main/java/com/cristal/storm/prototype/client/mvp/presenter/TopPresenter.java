package com.cristal.storm.prototype.client.mvp.presenter;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * This is the presenter of the top placeholder
 *
 */
public class TopPresenter extends
        Presenter<TopPresenter.TopViewInterface, TopPresenter.TopProxy> {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    public static final String nameToken = "top";
    
    private final PlaceManager placeManager;

    private DispatchAsync dispatcher;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    @ProxyCodeSplit
    @NameToken("top")
    public interface TopProxy extends Proxy<TopPresenter>, Place {
    }

    public interface TopViewInterface extends View {
        void setPlaceholderDescriptor(String description);
        public void setContentA();
        public void setContentB();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public TopPresenter(EventBus eventBus, TopViewInterface view, TopProxy proxy,
            PlaceManager placeManager, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
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
        //RevealRootContentEvent.fire(this, this);
        //RevealContentEvent.fire(this, AppStartPagePresenter.TYPE_SetTabContent, this);
    }
    
    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        
        String typeParam = request.getParameter("type", "");

        // Select the what your content will be
        if (typeParam.compareTo("info") == 0) {
            getView().setContentA();
        }
        else if (typeParam.compareTo("phone") == 0) {
            getView().setContentB();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}