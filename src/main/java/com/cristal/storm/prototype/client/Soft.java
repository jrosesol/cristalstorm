package com.cristal.storm.prototype.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.cristal.storm.prototype.client.gin.MyGinjector;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Soft implements EntryPoint {

    public final MyGinjector ginjector = GWT.create(MyGinjector.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // Register all which need delayed binding
        DelayedBindRegistry.bind(ginjector);

        // Go to the default place revealDefaultPlace() token
        ginjector.getPlaceManager().revealCurrentPlace();
        
        // FIXCOMMIT //
        // REMOVE THIS WHEN REAL APP IS DEPLOYED
        ginjector.getDemoDataLoader().loadDemoData();
    }
}
