/**
 *
 *
 * @author Jose Rose
 * 2011-03-14
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent;
import com.cristal.storm.prototype.client.event.UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler;
import com.cristal.storm.prototype.client.mvp.view.MceCollectionWidgetUiHandlers;
import com.cristal.storm.prototype.shared.domain.MceDto;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

import com.google.gwt.event.shared.EventBus;

/**
 * MceCollectionWidget Presenter implementation
 * Handles the display of the "Memory Cueu Elements".
 */
public class MceCollectionWidgetPresenter extends
    PresenterWidget<MceCollectionWidgetPresenter.MceCollectionWidgetViewInterface>
        implements MceCollectionWidgetUiHandlers {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////

    private final DataStoreProxy dataProxy;
    
    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link MceCollectionWidgetPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface MceCollectionWidgetViewInterface extends View,
            HasUiHandlers<MceCollectionWidgetUiHandlers> {
        public void addMceToCollection(String uriText, String tagsText);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public MceCollectionWidgetPresenter(EventBus eventBus,
            MceCollectionWidgetViewInterface view, PlaceManager placeManager,
            DispatchAsync dispatcher, DataStoreProxy dataProxy) {
        super(eventBus, view);
        getView().setUiHandlers(this);
        
        this.dataProxy = dataProxy;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onBind() {
      super.onBind();
      addRegisteredHandler( UpdateDataBindedObjectsEvent.getType(), new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {

            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent event) {
                System.out.print("Ready to server");
                
                System.out.print("\n" + event.getMceDto().uri + ";\t" + event.getMceDto().tag.toString());
                
                for (String tag : event.getMceDto().tag) {
                    getView().addMceToCollection(event.getMceDto().uri, tag);
                }
            }
          } );
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////

}
